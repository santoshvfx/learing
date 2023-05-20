// $Id: LIMIDLPrintFormatter.java,v 1.3 2008/02/29 22:45:17 gg2712 Exp $
package com.sbc.eia.bis.lim.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import com.sbc.eia.idl.types.ObjectProperty;

/**
 * This class uses the Java Reflection API to print an IDL object.
 * This is based loosely on code originally written by Matt Hicks.
 * @author: gg2712 
 */

public class LIMIDLPrintFormatter
{
	private StringBuffer sb;
	private int level = 0;
	private String[] indent =
		{
			"",
			" ",
			"  ",
			"   ",
			"    ",
			"     ",
			"      ",
			"       ",
			"        ",
			"         ",
			"          ",
			"           ",
			"            ",
			"             ",
			"              ",
			"               ",
			"                ",
			"                 ",
			"                  ",
			"                   ",
			"                    ",
			"                     ",
			"                      ",
			"                       ",
			"                        ",
			"                         " 
		};

	/**
	 * main method for testing
	 */
	public static void main(String[] args)
	{
		String[] anArray = { "hello", "there", "", " ", "sayonara" };
		String s = "gumbo";

		int[] i = { 777, 0, 0, 23, 78 };

		LIMIDLPrintFormatter objPrinter = new LIMIDLPrintFormatter();
		System.out.println(objPrinter.print(anArray, "anArray"));
		System.out.println(objPrinter.print(s, "string"));
		//objPrinter.print(buf, i, "anInt");
		
	}

	/**
	 * Constructor
	 */
	public LIMIDLPrintFormatter()
	{
		super();
	}

	/**
	 * Print an int
	 */
	public String print(int aInt, String name)
	{
		try
		{
			sb = new StringBuffer();

			sb.append(name + "=" + aInt);
		}
		catch (Exception e)
		{
			if (sb == null)
			{
				sb = new StringBuffer();
			}
			sb.append("*** error occured ***");
		}
		return sb.toString();
	}
	
	/**
	 * Print an int
	 */
	public String print(boolean b, String name)
	{
		try
		{
			sb = new StringBuffer();
			sb.append(name + "=" + b);
		}
		catch (Exception e)
		{
			if (sb == null)
			{
				sb = new StringBuffer();
			}
			sb.append("*** error occured ***");
		}
		return sb.toString();
	}
		
	/**
	 * print method for printing the General Message
	 * 
	 * @param StringBuffer strBuf
	 * @param Object[]obj
	 * @param name
	 * @return StringBuffer
	 */
	public String print(Object[] obj, String name)
	{
		try
		{
			sb = new StringBuffer();
			sb.append(name + ":");
			for (int i = 0; i < obj.length; i++)
			{
				printObject(obj[i]);
			}

		}
		catch (Exception e)
		{
			if (sb == null)
			{
				sb = new StringBuffer();
			}
			sb.append("*** error occured ***");
		}

		return sb.toString();
	}

	/**
	 * Print object and name
	 */
	public String print(Object object, String name)
	{
		try
		{
			sb = new StringBuffer();

			sb.append(name + "=");
			printObject(object);
		}
		catch (Exception e)
		{
			if (sb == null)
			{
				sb = new StringBuffer();
			}
			sb.append("*** error occured ***");
		}
		return sb.toString();
	}

	/**
	 * Print an object
	 */
	private void printObject(Object object)
	{
		if (object != null)
		{
			// Check the object type
			if (object.getClass().isArray())
			{
				//System.out.println("class is"  + object.getClass().getName() );
				printArray((Object[]) object);
			}
			else if (isPrimitive(object))
			{
				printPrimitiveObject(object);
			}
			else if (object.getClass().getName().trim().equalsIgnoreCase("com.sbc.eia.idl.types.ObjectProperty"))
			{
				printObjectProperty((ObjectProperty) object);
			}
			else
			{
				//go through each field

				Field[] objectFields = object.getClass().getDeclaredFields();
				level++;
				int i = objectFields.length - 1;
				for (; i > -1; i--)
				{
					//printField(object, objectFields[i].getName());
					printField(object, objectFields[i]);
				}
				level--;
			}
		}
		else
		{
			sb.append("{null}");
		}
	}

	/**
	 * Print an oject and field
	 */
	private void printField(Object object, Field field)
	{
		// Print each field only if they are not constants
		try
		{
			if (!(Modifier.isPublic(field.getModifiers())
				&& Modifier.isStatic(field.getModifiers())
				&& Modifier.isFinal(field.getModifiers())))
			{
				// Do not print the discriminator and the uninitialized
				if (isValidField(field.getName()))
				{
					// Print the Field Header except if it's the theValue field of an Opt field
					if (!field.getName().endsWith("theValue"))
					{
						sb.append("\n");
						sb.append(getIndent(level));
						
						sb.append(field.getName().replace('_', ' ').trim());
						sb.append("=");
					}

					if (Modifier.isPublic(field.getModifiers())
						&& !Modifier.isStatic(field.getModifiers())
						&& !Modifier.isFinal(field.getModifiers()))
					{
						// field is public and not final nor static
						try
						{
							printObject(field.get(object));
						}
						catch (IllegalAccessException e)
						{
							//Ignore
						}
					}
					else
					{
						// Look for a getter accessor
						try
						{
							Object value = getAccessorValue(object, field.getName(), field);
							if (value != null)
							{
								printObject(value);
							}
						}
						catch (NoSuchMethodException e)
						{
							// Ignore - Assume private variable
						}
					}
				}
			}
		}
		catch (Exception e)
		{
		}
	}

	/**
	 * getAccessorValue
	 */
	private Object getAccessorValue(Object object, String fieldName, Field field) throws NoSuchMethodException
	{
		// Remove all underscores from the fieldName
		fieldName = fieldName.replace('_', ' ');
		fieldName = fieldName.trim();
		Method[] methods = object.getClass().getDeclaredMethods();

		for (int i = 0; i < methods.length; i++)
		{
			String methodName = methods[i].getName().toUpperCase();

			if (methodName.indexOf(fieldName.toUpperCase()) != -1
				&& (field.getType().isAssignableFrom(methods[i].getReturnType())))
			{
				try
				{
					Method method = methods[i];
					// Make sure there are no parameters required
					if (method.getParameterTypes().length == 0)
					{
						Object value = method.invoke(object, null);
						return value;
					}
				}
				catch (org.omg.CORBA.BAD_OPERATION e)
				{
					return "**Unitialized**";
				}
				catch (Exception e)
				{
					// Ignore, keep looking for accessor
				}
			}
		}

		throw new NoSuchMethodException("The method was not found.");
	}

	/**
	 * isPrimitive
	 */
	private boolean isPrimitive(Object object)
	{
		if (object instanceof Boolean)
		{
			return true;
		}
		if (object instanceof Integer)
		{
			return true;
		}
		if (object instanceof Float)
		{
			return true;
		}
		if (object instanceof Double)
		{
			return true;
		}
		if (object instanceof Long)
		{
			return true;
		}
		if (object instanceof Short)
		{
			return true;
		}
		if (object instanceof String)
		{
			return true;
		}

		return false;
	}

	/**
	 * isValidField
	 */
	private boolean isValidField(String fieldName)
	{
		// Remove the underscores if any
		fieldName = fieldName.replace('_', ' ');
		fieldName = fieldName.trim();

		if (fieldName.equalsIgnoreCase("discriminator"))
		{
			return false;
		}

		if (fieldName.equalsIgnoreCase("uninitialized"))
		{
			return false;
		}

		return true;
	}

	/**
	 * printArray
	 */
	private void printArray(Object[] array)
	{
		level++;
		// Go through each element and print
		for (int i = 0; i < array.length; i++)
		{
			sb.append("\n");
			sb.append(getIndent(level));
			sb.append((array[i].getClass().getName()) + "[" + i + "]:");
			printObject(array[i]);
		}
		level--;
	}

	/**
	 * printFieldHeader
	 */
	private String printFieldHeader(String name, int offset)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(name + "=");
		return sb.toString();
	}

	/**
	 * printPrimitiveObject
	 */
	private void printPrimitiveObject(Object object)
	{
		if (object == null)
		{
			sb.append("null");
		}
		else
		{
			if (object instanceof String)
			{
				String s = (String) object;
				if (s.length() == 0)
					sb.append("");
				else
					sb.append(s);
			}
			else
			{
				sb.append(object.toString());
			}
		}
	}

	private void printObjectProperty(ObjectProperty op)
	{
		if (op == null)
		{
			sb.append("null");
			return;
		}

		sb.append(op.aTag);
		sb.append("=");
		sb.append(op.aValue);

	}
	
	private String getIndent(int i)
	{
		if(i<indent.length)
		{
			return indent[i];
		}
		else
		{
			return indent[i] + "max indent reached - current level: " + i;
		}
	}
}
