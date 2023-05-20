# LIM Bis

## BIS to BIS replacement

1. Upgrade your common rest version to at least 1.1.4 pom to use the following dependencies:
```xml
<!-- idl-common_types -->
<dependency>
   <groupId>com.sbc.eia</groupId>
   <artifactId>idl-internal-common_types</artifactId>
   <version>1.0.1</version>
</dependency>
<dependency>
   <groupId>com.att.lms.bis</groupId>
   <artifactId>att-lms-response-handler</artifactId>
   <version>1.0.2</version>
</dependency>
```
2. In [application-e2e-test.properties](src/e2e-test/resources/application-e2e-test.properties)
   and [application.properties](src/main/resources/application.properties), add the properties for your BIS rest call,
   e.g.:

```
bis.restEndpoints.sm.scheme=https
bis.restEndpoints.sm.host=smbis-nprd.az.3pc.att.com
bis.restEndpoints.sm.port=443
bis.restEndpoints.sm.path=restservices/lms/v1/sm/
bis.restEndpoints.sm.username=m49476@lms.att.com
bis.restEndpoints.sm.password=Middleware$123
```

3. Add [BisRestEndpointSettings.java](src/main/java/com/att/lms/bis/rest/config/settings/BisRestEndpointSettings.java)
   to grab the bis prefixed properties in the application properties
4. Update [RestConstants.java](src/main/java/com/att/lms/bis/rest/config/settings/RestConstants.java) to include any BIS
   app you'll be talking to via rest
5. Add a Dao class (e.g. [SmRestDao.java](src/main/java/com/att/lms/bis/rest/dao/SmRestDao.java) for each BIS app to hit
   via REST
    1. Add a method for each endpoint you want to hit
6. Add a RestService class (e.g. [SmRestService.java](src/main/java/com/att/lms/bis/rest/service/SmRestService.java))
   that, for each called endpoint, takes in the corresponding Request DTO and calls the corresponding DAO method
7. Add a RestFacade class (e.g. [SmRestFacade.java](src/main/java/com/att/lms/bis/rest/facade/SmRestFacade.java))
    * Have it implement the BIS app's "facade" (e.g. SmFacade))
    * For each method you need to call via REST:
        * Build the RequestDto from the input parameters
        * Call the REST Service
        * Parse out the Either object returned to return the response object or the BIS Context
8. In [SmClient.java](src/main/java/com/sbc/eia/bis/RmNam/utilities/SmClient/SmClient.java), change the getBean method
   to just use the StaticContextAccessor to get the SM Facade bean,
   e.g.: `return StaticContextAccessor.getBean(SmFacade.class);`
9. Use the default constructor for SmClient now:
    * [BRMS.java](src/main/java/com/sbc/eia/bis/BusinessInterface/ServiceAddress/brms/BRMS.java)
    * [TestSMClient.java](src/main/java/com/sbc/eia/bis/facades/lim/ejb/tests/TestSMClient.java)
    * [ASON.java](src/main/java/com/sbc/eia/bis/BusinessInterface/ason/ASON.java)

## OVALS Replacements

These steps were largely the same for the different OVALS replacements
1. Create new package ovals in src.main.java.com.att.lms.bis.service
2. Populate package with more generic / reused data types
3. Create new package model.pavs within the ovals package and add a request package and response package
4. In the request package, create a [PavsRequest.java](src/main/java/com/att/lms/bis/service/ovals/model/pavs/request/PavsRequest.java) from the provided wsdl / request xml
   1. Use @XmlElement or @JSONProperty annotations to force uppercase naming where needed, depending on the format the service wants to take in.
      1. Incorrect naming / capitalization will result in errors due to bad request / no data returning
   2. Also create a [package-info.java](src/main/java/com/att/lms/bis/service/ovals/model/pavs/request/package-info.java) and a [ObjectFactory.java](src/main/java/com/att/lms/bis/service/ovals/model/pavs/request/ObjectFactory.java)
5. Repeat step 4 in the response package.
6. In the ovals package, create file [OvalsPav](src/main/java/com/att/lms/bis/service/ovals/OvalsPav.java) and [OvalsPavApi](src/main/java/com/att/lms/bis/service/ovals/OvalsPavApi.java)
   1. These files are specific to the api. When implementing a new OVALS replacement, create these files, replacing 'Pav' with whatever the new service is
7. Also in the ovals package create file [OvalsWrapper](src/main/java/com/att/lms/bis/service/ovals/OvalsWrapper.java)
   1. Create two mapping methods, one from the normal method request to the new PavsRequest and one from the new PavsResponse to the normal method response, trying to map as accurately as possible across objects.
   2. Recreate method that needs the ovals replacement, hooking in the ovals call and calling the two mapping functions.
8. In [lim.properties.NON271SAT](src/main/resources/lim.properties.NON271SAT), add properties for new API call including username/password + hostname/url
9. In [LimBean.java](src/main/java/com/sbc/eia/bis/facades/lim/ejb/LimBean.java) find the method that needs the replacement. Comment out existing 'retVal = ...' call. Instantiate a new OvalsWrapper object and use that wrapper to call method, so it uses the mappings and calls from [OvalsWrapper.java](src/main/java/com/att/lms/bis/service/ovals/OvalsWrapper.java)
10. Update E2E test with any test data you may have, comment out that method from the [LimLegacyStubService.java](src/e2e-test/java/com/att/lms/bis/service/LimLegacyStubService.java), and run the test. You'll probably have to update the field descriptors in that E2E class.