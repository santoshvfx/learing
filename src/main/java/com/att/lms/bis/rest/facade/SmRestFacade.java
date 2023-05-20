package com.att.lms.bis.rest.facade;

import com.att.lms.bis.LmsRestResponseHandler;
import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.dto.sm.GetServiceLocationRequestDto;
import com.att.lms.bis.dto.sm.PingRequestDto;
import com.att.lms.bis.rest.service.SmRestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.sm.*;
import com.sbc.eia.idl.sm_types.SubscriptionAccountInformationType;
import com.sbc.eia.idl.sm_types.SubscriptionAccountInformationTypeOpt;
import com.sbc.eia.idl.types.*;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.omg.CORBA.*;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.RemoveException;
import java.io.IOException;
import java.rmi.RemoteException;

@Component
public class SmRestFacade implements SmFacade {

    @Autowired
    SmRestService smRestService;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public PingReturn ping(BisContext bisContext) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound, MultipleExceptions {

        PingRequestDto requestDto = PingRequestDto.builder()
                .aBisContext(bisContext).build();

        Either<Throwables, Option<com.sbc.eia.idl.sm.PingReturn>> result =
                smRestService.ping(requestDto, new ParameterizedTypeReference<PingReturn>() {});

        return LmsRestResponseHandler.parse(result);
    }

    @Override
    public GetServiceAddressReturn getServiceLocation(BisContext bisContext, ObjectKey objectKey) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        GetServiceLocationRequestDto requestDto = GetServiceLocationRequestDto.builder()
                .aBisContext(bisContext)
                .aPartialServiceHandle(objectKey).build();

        Either<Throwables, Option<com.sbc.eia.idl.sm.GetServiceAddressReturn>> result =
                smRestService.getServiceLocation(requestDto, new ParameterizedTypeReference<GetServiceAddressReturn>() {});

        return LmsRestResponseHandler.parseExcludingMultiple(result);
    }

    @Override
    public SelfTestReturn selfTest(BisContext bisContext) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound, MultipleExceptions {
        return null;
    }

    @Override
    public ProductSubscriptionListForAccountNumberReturn retrieveProductSubscriptionsForAccountNumber(BisContext bisContext, ObjectKey objectKey, String s) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public SubscriptionAccountReturn retrieveSubscriptionAccountsForAffiliatesByAccountNumber(BisContext bisContext, ObjectKey objectKey, StringOpt stringOpt, String[] strings, SubscriptionAccountInformationTypeOpt subscriptionAccountInformationTypeOpt, LongOpt longOpt, StringOpt stringOpt1, EiaDateOpt eiaDateOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public SubscriptionAccountReturn retrieveSubscriptionAccountsForAffiliatesByAccountNumber2(BisContext bisContext, ObjectKey objectKey, StringOpt stringOpt, String[] strings, SubscriptionAccountInformationTypeOpt subscriptionAccountInformationTypeOpt, LongOpt longOpt, StringOpt stringOpt1, EiaDateOpt eiaDateOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public SubscriptionAccountReturn retrieveSubscriptionAccountsForAffiliateAccountNumbers(BisContext bisContext, CompositeObjectKey[] compositeObjectKeys, LongOpt longOpt, StringOpt stringOpt, EiaDateOpt eiaDateOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public SubscriptionAccountReturn retrieveSubscriptionAccountsForAffiliateAccountNumbers2(BisContext bisContext, CompositeObjectKey[] compositeObjectKeys, LongOpt longOpt, StringOpt stringOpt, EiaDateOpt eiaDateOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public SubscriptionAccountReturn retrieveSubscriptionAccountsForAccountNumber(BisContext bisContext, ObjectKey objectKey, StringOpt stringOpt, String s, SubscriptionAccountInformationTypeOpt subscriptionAccountInformationTypeOpt, LongOpt longOpt, StringOpt stringOpt1, EiaDateOpt eiaDateOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public SubscriptionAccountReturn retrieveSubscriptionAccountsForAccountNumber2(BisContext bisContext, ObjectKey objectKey, StringOpt stringOpt, String s, SubscriptionAccountInformationTypeOpt subscriptionAccountInformationTypeOpt, LongOpt longOpt, StringOpt stringOpt1, EiaDateOpt eiaDateOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public EiaIteratorReturn retrieveSubscriptionAccountServicesIteratorByAccountKey(BisContext bisContext, CompositeObjectKey compositeObjectKey, TimeDurationOpt timeDurationOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public SubscriptionAccountServicesReturn getNextSubscriptionAccountServicesByIterator(BisContext bisContext, EiaIterator eiaIterator) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public SubscriptionAccountServicesReturn getNextSubscriptionAccountServicesByIterator2(BisContext bisContext, EiaIterator eiaIterator) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public SubscriptionAccountReturn retrieveSubscriptionAccountsForService(BisContext bisContext, ObjectKey objectKey, String s, SubscriptionAccountInformationType subscriptionAccountInformationType, StringOpt stringOpt, EiaDateOpt eiaDateOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public SubscriptionAccountReturn retrieveSubscriptionAccountsForService2(BisContext bisContext, ObjectKey objectKey, String s, SubscriptionAccountInformationType subscriptionAccountInformationType, StringOpt stringOpt, EiaDateOpt eiaDateOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public ServiceProvisioningReturn determineServiceProvisioning(BisContext bisContext, ObjectKey objectKey) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public GetServicePropertiesReturn getServiceProperties(BisContext bisContext, ObjectKey objectKey, String[] strings, Identifier[] identifiers) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public GetServicePropertiesReturn getServiceProperties2(BisContext bisContext, ObjectKey objectKey, String[] strings, Identifier[] identifiers) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public ServiceListForSubscriptionAccount retrieveServiceListForSubscriptionAccount(BisContext bisContext, ObjectKey objectKey) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        return null;
    }

    @Override
    public ServiceListForSubscriptionAccount2 retrieveServiceListForSubscriptionAccount2(BisContext bisContext, ObjectKey objectKey) throws BusinessViolation, ObjectNotFound, DataNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented {
        return null;
    }

    @Override
    public GetSubscriptionAccountPropertiesReturn getSubscriptionAccountProperties(BisContext bisContext, CompositeObjectKey compositeObjectKey, String[] strings, Identifier[] identifiers) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public RetrieveSubscriptionAccountPropertiesForAccountNumbersReturn retrieveSubscriptionAccountPropertiesForAccountNumbers(BisContext bisContext, ObjectKey[] objectKeys) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public retrieveServiceListForPartialServiceReturn retrieveServiceListForPartialService(BisContext bisContext, ObjectKey objectKey, StringOpt stringOpt, StringOpt stringOpt1) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public boolean _is_a(String repositoryIdentifier) {
        return false;
    }

    @Override
    public boolean _is_equivalent(Object other) {
        return false;
    }

    @Override
    public boolean _non_existent() {
        return false;
    }

    @Override
    public int _hash(int maximum) {
        return 0;
    }

    @Override
    public Object _duplicate() {
        return null;
    }

    @Override
    public void _release() {

    }

    @Override
    public Object _get_interface_def() {
        return null;
    }

    @Override
    public Request _request(String operation) {
        return null;
    }

    @Override
    public Request _create_request(Context ctx, String operation, NVList arg_list, NamedValue result) {
        return null;
    }

    @Override
    public Request _create_request(Context ctx, String operation, NVList arg_list, NamedValue result, ExceptionList exclist, ContextList ctxlist) {
        return null;
    }

    @Override
    public Policy _get_policy(int policy_type) {
        return null;
    }

    @Override
    public DomainManager[] _get_domain_managers() {
        return new DomainManager[0];
    }

    @Override
    public Object _set_policy_override(Policy[] policies, SetOverrideType set_add) {
        return null;
    }
}
