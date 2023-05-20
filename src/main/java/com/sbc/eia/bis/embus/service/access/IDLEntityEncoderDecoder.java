//$Id: IDLEntityEncoderDecoder.java,v 1.5 2004/05/11 02:35:13 as5472 Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.
 */

/** Description
 *  This file contains the IDLEntityEncoderDecoder class and any helpers that
 *  it might use.
 *  Description
 */
package com.sbc.eia.bis.embus.service.access;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.omg.CORBA.portable.IDLEntity;

import com.sbc.vicunalite.api.LongMsg;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBReader;
import com.sbc.vicunalite.vaxb.VAXBWriter;

/** Description
 *  The IDLEntityEncoderDecoder is a class that can be used to "encode" BIS
 *  IDL Objects into XML using VAXB, or "decode" XML into BIS IDL Objects
 *  using VAXB
 *  Description
 */
public class IDLEntityEncoderDecoder implements IEncoderDecoder
{
//  counters used to debug caching
//    static int objCons_gets = 0;
//    static int objCons_adds = 0;
//    static int objCls_gets = 0;
//    static int objCls_adds = 0;

    static Hashtable m_constructorAndObjectClassCache = null;

    /**
     * Constructor for IDLEntityEncoderDecoder.
     */
    public IDLEntityEncoderDecoder()
    {
        super();
        
        if ( m_constructorAndObjectClassCache == null ){
            m_constructorAndObjectClassCache = new Hashtable();
        }
    }
    
    /** Description
     *  The EncoderHelper class is used internally by the IDLEntityEncoder
     *  to perform the mapping that has to be performed for IDL Objects.
     *  In order to use VAXB to encode an IDL Object to XML
     *  you first need to instantiate the IDL Object's associated 
     *  MMarshalObject message object by passing the IDL Object into the
     *  message object's constructor.  You then use the VAXBWriter to encode
     *  the message object into XML.
     *  
     *  For example:  To encode an Address object, you would instantiate an
     *  AddressMsg object by passing in the Address as an argument.  You
     *  then use the VAXBWriter's "encode" method to encode the AddressMsg
     *  object.  What results is XML representing the Address Object.
     * 
     *  The EncodeHelper reduces these steps by determining the associated
     *  message class for you.  It also instantiates the message class and
     *  passes the object as part of the process. 
     *  Description
     */
    private class EncoderHelper {
        private Object m_object = null;
        private boolean m_isPrimitive = false;
        private boolean m_isArray = false;
        private Class m_realClass = null;
        
        /**
         * Method EncoderHelper.
         * @param i_object
         * @throws EncoderException
         */
        public EncoderHelper( Object i_object ) throws EncoderException {
            m_object = i_object;

            // set up the helper with information about the object
            if ( i_object.getClass().isArray() ) {
                m_isArray = true;
                if ( (m_realClass = getPrimitiveClass( i_object.getClass().getComponentType() )) != null ) {
                    m_isPrimitive = true;
                } else {
                    m_isPrimitive = false;
                }
            } else {
                m_isArray = false;
                if ( (m_realClass = getPrimitiveClass( i_object.getClass() )) != null ) {
                    m_isPrimitive = true;
                } else {
                    m_isPrimitive = false;
                }
            }
            
            // use the information gathered about the object to determine
            // whether it is Encodable.
            isEncodable();
        }
        
        /**
         * Method constructMMarshalObject.
         * @return Object
         * @throws EncoderException
         */
        public Object constructMMarshalObject()
            throws EncoderException
        {
            Class idlObjectClass = m_object.getClass();
            Class mmarshalObjectClass = null;
            Constructor mmarshalObjectConstructor = null;
            Object mmarshalObject =  null;
            
            mmarshalObjectClass =
                getAssociatedMMarshalObjectClass(
                    idlObjectClass);

            convertObjectFromWrappedPrimitivesToPrimitives();

            mmarshalObjectConstructor =
                getAssociatedMMashalObjectConstructor(
                    idlObjectClass,
                    mmarshalObjectClass);
    
            try {
                mmarshalObject = mmarshalObjectConstructor.newInstance(new Object[]{m_object});
            } catch ( InstantiationException instantiationException ) {
                throw new EncoderException(instantiationException);
            } catch ( InvocationTargetException invocationTargetException ) {
                throw new EncoderException(invocationTargetException);            
            } catch ( IllegalAccessException illegalAccessException ) {
                throw new EncoderException(illegalAccessException);            
            }
            return mmarshalObject;
        }

        private void convertObjectFromWrappedPrimitivesToPrimitives()
        {
            /*
             * Convert wrapper primitives to real primitives.
             * The message classes for primtives takes primitives and not the wrappers.
             */
            if (m_object.getClass().getComponentType() == long.class ) {
                long[] longs = (long[])m_object;
                int[] primitives = new int[longs.length];
                for ( int j = 0 ; j < primitives.length ; j++ ) {
                    primitives[j] = new Long(longs[j]).intValue();
                }
                m_object = primitives;
            }
            
            /*
             * Note this message class treats a long array as an int array
             */
            if (m_object.getClass().getComponentType() == Long.class ) {
                Long[] longs = (Long[])m_object;
                int[] primitives = new int[longs.length];
                for ( int j = 0 ; j < primitives.length ; j++ ) {
                    primitives[j] = longs[j].intValue();
                }
                m_object = primitives;
            }
            
            if (m_object.getClass().getComponentType() == Integer.class ) {
                Integer[] integers = (Integer[])m_object;
                int[] primitives = new int[integers.length];
                for ( int j = 0 ; j < primitives.length ; j++ ) {
                    primitives[j] = integers[j].intValue();
                }
                m_object = primitives;
            }
            
            if (m_object.getClass().getComponentType() == Short.class ) {
                Short[] wrappers = (Short[])m_object;
                short[] primitives = new short[wrappers.length];
                for ( int j = 0 ; j < primitives.length ; j++ ) {
                    primitives[j] = wrappers[j].shortValue();
                }
                m_object = primitives;
            }
            
            if (m_object.getClass().getComponentType() == Double.class ) {
                Double[] wrappers = (Double[])m_object;
                short[] primitives = new short[wrappers.length];
                for ( int j = 0 ; j < primitives.length ; j++ ) {
                    primitives[j] = wrappers[j].shortValue();
                }
                m_object = primitives;
            }
            
            if (m_object.getClass().getComponentType() == Boolean.class ) {
                Boolean[] wrappers = (Boolean[])m_object;
                boolean[] primitives = new boolean[wrappers.length];
                for ( int j = 0 ; j < primitives.length ; j++ ) {
                    primitives[j] = wrappers[j].booleanValue();
                }
                m_object = primitives;
            }
            
            if (m_object.getClass().getComponentType() == Byte.class ) {
                Byte[] wrappers = (Byte[])m_object;
                byte[] primitives = new byte[wrappers.length];
                for ( int j = 0 ; j < primitives.length ; j++ ) {
                    primitives[j] = wrappers[j].byteValue();
                }
                m_object = primitives;
            }
            
            if (m_object.getClass().getComponentType() == Character.class ) {
                Character[] wrappers = (Character[])m_object;
                char[] primitives = new char[wrappers.length];
                for ( int j = 0 ; j < primitives.length ; j++ ) {
                    primitives[j] = wrappers[j].charValue();
                }
                m_object = primitives;
            }
        }
        
        /**
         * Method getAssociatedMMashalObjectConstructor.
         * @param idlObjectClass
         * @param mmarshalObjectClass
         * @return Constructor
         * @throws EncoderException
         */
        private Constructor getAssociatedMMashalObjectConstructor(
            Class idlObjectClass,
            Class mmarshalObjectClass)
            throws EncoderException
        {
            Constructor mmarshalObjectConstructor = null;
            mmarshalObjectConstructor = getFromAssociatedMMashalObjectConstructorCache( m_object.getClass() );

            if ( mmarshalObjectConstructor == null ) {
                try {
                
                    if ( m_isPrimitive && m_isArray ) {
                        
                        if ( m_realClass == int.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ int[].class });
                        } else if ( m_realClass == long.class ) {
                            /*
                             * LongSeqMsg class takes ints not longs
                             */
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ int[].class });
                        } else if ( m_realClass == double.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ double[].class });
                        }else if ( m_realClass == short.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ short[].class });
                        } else if ( m_realClass == float.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ float[].class });
                        } else if ( m_realClass == char.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ char[].class });
                        }else if ( m_realClass == byte.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ byte[].class });
                        } else if ( m_realClass == boolean.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ boolean[].class });
                        } else if ( m_realClass == String.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ String[].class });
                        }
                    } else if ( m_isPrimitive && !m_isArray ) {
        
                        if ( m_realClass == int.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ int.class });
                        } else if ( m_realClass == long.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ long.class });
                        }else if ( m_realClass == double.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ double.class });
                        }else if ( m_realClass == short.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ short.class });
                        } else if ( m_realClass == float.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ float.class });
                        } else if ( m_realClass == char.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ char.class });
                        } else if ( m_realClass == byte.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ byte.class });
                        } else if ( m_realClass == boolean.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ boolean.class });
                        } else if ( m_realClass == String.class ) {
                            mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{ String.class });
                        }
                    } else if ( !m_isPrimitive && m_isArray ) {
                        mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{idlObjectClass});
                    } else if ( !m_isPrimitive && !m_isArray ) {
                        mmarshalObjectConstructor = mmarshalObjectClass.getConstructor(new Class[]{idlObjectClass});
                    }

                    addToAssociatedMMashalObjectConstructorCache( m_object.getClass(), mmarshalObjectConstructor );

                } catch ( NoSuchMethodException noSuchMethodException) {
                    throw new EncoderException(noSuchMethodException);            
                }

            }
                       
            return mmarshalObjectConstructor;
        }

    
        /**
         * Method getAssociatedMMarshalObjectClass.
         * @param idlObjectClass
         * @return Class
         * @throws EncoderException
         */
        private Class getAssociatedMMarshalObjectClass(
            Class idlObjectClass)
            throws EncoderException
        {
            Class mmarshalObjectClass = null;
            mmarshalObjectClass = getFromAssociatedMMarshalObjectClassCache( m_object.getClass() );
            
            if ( mmarshalObjectClass == null )
            {
            
                try {
                
                    if ( m_isPrimitive) {
                        int lastIndexOfPeriodInClassName = m_realClass.getName().lastIndexOf('.');
                        String realClassName = m_realClass.getName().substring( lastIndexOfPeriodInClassName + 1 , m_realClass.getName().length() );
                        String upperCaseFirstCharInName = realClassName.substring(0,1).toUpperCase() + realClassName.substring(1, realClassName.length());
                        
                        if ( m_isArray ) {
                            mmarshalObjectClass = Class.forName( "com.sbc.eia.idl.types." + upperCaseFirstCharInName + "SeqMsg");
                        } else {
                            mmarshalObjectClass = Class.forName( LongMsg.class.getPackage().getName() + "." +  upperCaseFirstCharInName + "Msg");
                        }
                    } else {    
                        if ( m_isArray ) {
                            mmarshalObjectClass = Class.forName( idlObjectClass.getComponentType().getName() + "SeqMsg");
                        } else {
                            mmarshalObjectClass = Class.forName( idlObjectClass.getName() + "Msg");
                        }
                    }
                
                    addTogetAssociatedMMarshalObjectClassCache( m_object.getClass(), mmarshalObjectClass);                
                
                } catch ( ClassNotFoundException classNotFoundException ) {
                    throw new EncoderException(classNotFoundException);            
                }
            }
            return mmarshalObjectClass;
        }
        
        /**
         * Method getPrimitiveClass determines whether provided class
         * represents a primitive or primitive wrapper.
         * @param possiblePromitiveWrapperClass the Class to be tested to
         * determine whether it is a primitive class, primitive wrapper class,
         * or otherwise.
         * @return Class if the privided class is a primitive wrapper the 
         * primitive version of the class is returned, if the provided class
         * is primitive, the primitive class is returned.  Null is returned
         * if the class is neither a primitive wrapper nor a primitive class.
         */
        private Class getPrimitiveClass( Class possiblePromitiveWrapperClass ) {
            Class primitiveClass = null;
    
            if ( possiblePromitiveWrapperClass == Integer.class || possiblePromitiveWrapperClass == int.class) {
                primitiveClass = int.class;
            }
            
            if ( possiblePromitiveWrapperClass == Long.class || possiblePromitiveWrapperClass == long.class ) {
                primitiveClass = long.class;
            }
            
            if ( possiblePromitiveWrapperClass == Double.class || possiblePromitiveWrapperClass == double.class) {
                primitiveClass = double.class;
            }
            
            if ( possiblePromitiveWrapperClass == Short.class || possiblePromitiveWrapperClass == short.class) {
                primitiveClass = short.class;
            }
            
            if ( possiblePromitiveWrapperClass == Float.class || possiblePromitiveWrapperClass == float.class) {
                primitiveClass = float.class;
            }
            
            if ( possiblePromitiveWrapperClass == Character.class || possiblePromitiveWrapperClass == char.class) {
                primitiveClass = char.class;
            }
            
            if ( possiblePromitiveWrapperClass == Byte.class || possiblePromitiveWrapperClass == byte.class) {
                primitiveClass = byte.class;
            }
            
            if ( possiblePromitiveWrapperClass == Boolean.class || possiblePromitiveWrapperClass == boolean.class) {
                primitiveClass = boolean.class;
            }
            
            if ( possiblePromitiveWrapperClass == String.class ) {
                primitiveClass = String.class;
            }
    
            return primitiveClass;
        }
        
        /**
         * Method isEncodable determines whether the object is an encodeable
         * object.  Only primitive classes (including String) and primitive
         * wrappers, IDL Entities, and arrays of the aforementioned types are
         * encodable.
         * @throws EncoderException
         */
        private void isEncodable() throws EncoderException {
            boolean isEncodable = false;
            
            if ( m_isArray ) {
                if ( m_object instanceof IDLEntity[] ) {
                    isEncodable = true;
                }
                
                if ( m_realClass != null ) {
                    isEncodable = true;
                }
            } else {
                if ( m_realClass != null ) {
                    isEncodable = true;
                }
                
                if ( m_object instanceof IDLEntity ){
                    isEncodable = true;
                }
                
            }
            
            if ( !isEncodable ) {
                throw new EncoderException("Object of class " + m_object.getClass() + " is unencodable." );
            }
            
            return;
        }
    }


    /**
     * @see com.sbc.eia.bis.embus.service.access.IEncoder#encode(Object[])
     */
    public String encode(
        Object[] idlObjectArray)
        throws EncoderException
    {
        ArrayList mmarshalObjectArrayList = new ArrayList();
        if ( idlObjectArray != null ) {
            
            for ( int i = 0 ; i < idlObjectArray.length ; i++ ) {
        
                if ( idlObjectArray[i] != null ) {
                    
                    mmarshalObjectArrayList.add(new EncoderHelper(idlObjectArray[i]).constructMMarshalObject());
                    
                } else {
                    throw new EncoderException("IDL Object [" + i + "] in array is null.");
                }
            }
        }
        
        Object[] objects = mmarshalObjectArrayList.toArray();
        MMarshalObject[] mmarshalInputObjectArray = new MMarshalObject[objects.length];
        for ( int i = 0 ; i < mmarshalInputObjectArray.length ; i++ ) {
            mmarshalInputObjectArray[i] = (MMarshalObject)objects[i];
        }
        
        String inputMessage = null;
        try {
            inputMessage = VAXBWriter.encode(mmarshalInputObjectArray);
        } catch (IOException exception) {
            throw new EncoderException(exception);
        }
        return inputMessage;
    }


    /**
     * @see com.sbc.eia.bis.embus.service.access.IDecoder#decode(String)
     */
    public Object[] decode(
        String message)
        throws DecoderException
    {
        MMarshalObject[] mmarshalOutputObjectArray = null;
        try {
            mmarshalOutputObjectArray = VAXBReader.decodeAll(message);
        } catch ( ClassNotFoundException classNotFoundException ) {
            throw new DecoderException(classNotFoundException);            
        } catch (IOException exception) {
            throw new DecoderException(exception);
        }
        
        ArrayList returnObjectList = new ArrayList();
        
        if ( mmarshalOutputObjectArray != null ) {
                for ( int i = 0 ; i < mmarshalOutputObjectArray.length ; i++ ) {
                    MMarshalObject currentMMarshalObject = mmarshalOutputObjectArray[i];
                    Object currentIDLEntity = null;
                                        
                    if ( currentMMarshalObject != null ) {
                        Class IDLEntityClass = currentMMarshalObject.getClass();
                        Field valueField = null;
                        try {
                            valueField = IDLEntityClass.getField("value");
                        } catch ( NoSuchFieldException noSuchFieldException) {
                            throw new DecoderException(noSuchFieldException);            
                        }
                        
                        try {
                            currentIDLEntity = valueField.get(currentMMarshalObject);
                        } catch ( IllegalAccessException illegalAccessException ) {
                            throw new DecoderException(illegalAccessException);            
                        }
        
                    } else {
                        throw new DecoderException("Marshalled return object [" + i + "] in array is null.");
                    }
                    
                    returnObjectList.add(currentIDLEntity);
        
                }
                
        } else {
            throw new DecoderException("Array of marshalled return objects is null.");
        }
        
        Object[] idlReturnObjectArray  = (Object[])returnObjectList.toArray();
        return idlReturnObjectArray;
    }

    /**
     * Method getFromAssociatedMMashalObjectConstructorCache.
     * @param isPrimitive
     * @param isArray
     * @param aClass
     * @return Constructor
     */
    private Constructor getFromAssociatedMMashalObjectConstructorCache( Class aClass )
    {
//        objCons_gets++;
        Constructor mmarshalObjectConstructor = null;
            mmarshalObjectConstructor = (Constructor) m_constructorAndObjectClassCache
                .get( "Constructor: " + aClass);
//        out("get for: Constructor: " + aClass +" = " + mmarshalObjectConstructor);

        return mmarshalObjectConstructor;
    }

    

    /**
     * Method addToAssociatedMMashalObjectConstructorCache.
     * @param isPrimitive
     * @param isArray
     * @param aClass
     * @param mmarshalObjectConstructor
     */
    private void addToAssociatedMMashalObjectConstructorCache( Class aClass, Constructor mmarshalObjectConstructor)
    {
//        objCons_adds++;
//        out("add with: Constructor: " + aClass +" = " + mmarshalObjectConstructor);
        m_constructorAndObjectClassCache
            .put( "Constructor: " + aClass, mmarshalObjectConstructor);

    }
    

    /**
     * Method getFromAssociatedMMarshalObjectClassCache.
     * @param isPrimitive
     * @param isArray
     * @param aClass
     * @return Class
     */
    private Class getFromAssociatedMMarshalObjectClassCache( Class aClass )
    {
//        objCls_gets++;
        Class mmarshalObjectClass = null;
            mmarshalObjectClass = (Class) m_constructorAndObjectClassCache
                .get( "ObjectClass: " + aClass);
//        out("get for: ObjectClass: " + aClass +" = " + mmarshalObjectClass);

        return mmarshalObjectClass;
    }

    /**
     * Method addTogetAssociatedMMarshalObjectClassCache.
     * @param isPrimitive
     * @param isArray
     * @param aClass
     * @param mmarshalObjectClass
     */
    private void addTogetAssociatedMMarshalObjectClassCache( Class aClass, Class mmarshalObjectClass)
    {
//        objCls_adds++;
//        out("add with: ObjectClass: " + aClass +" = " + mmarshalObjectClass);
        m_constructorAndObjectClassCache
            .put( "ObjectClass: " + aClass, mmarshalObjectClass);

    }


/* ======================================================= *
DEBUGGING METHODS
* ======================================================= */

    private static void out( Object s ) {
        System.out.println(s);
    }
    
    private static void out( boolean s ) {
        System.out.println(s);
    }    
}
