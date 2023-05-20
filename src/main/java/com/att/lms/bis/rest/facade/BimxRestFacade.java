package com.att.lms.bis.rest.facade;

import com.att.lms.bis.LmsRestResponseHandler;
import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.dto.bimx.PingRequestDto;
import com.att.lms.bis.dto.bimx.AddNotesToBillingAccountRequestDto;
import com.att.lms.bis.rest.service.BimxRestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbc.eia.idl.bim_types.AddNotesToBillingAccountReturn;
import com.sbc.eia.idl.bimx.*;
import com.sbc.eia.idl.bimx.PingReturn;
import com.sbc.eia.idl.bimx.SelfTestReturn;
import com.sbc.eia.idl.bimx_types.CreateBillingAccountRequest;
import com.sbc.eia.idl.bimx_types.InstallmentBillingArrangementRequest;
import com.sbc.eia.idl.bimx_types.ModifyBillingAccountProfileRequest;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.types.*;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.omg.CORBA.*;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;

@Component
public class BimxRestFacade implements BimxFacade {

    @Autowired
    BimxRestService bimxRestService;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public PingReturn ping(BisContext bisContext) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound, MultipleExceptions {

        PingRequestDto requestDto = PingRequestDto.builder()
                .aBisContext(bisContext).build();

        Either<Throwables, Option<PingReturn>> result =
                bimxRestService.ping(requestDto, new ParameterizedTypeReference<PingReturn>() {});

        return LmsRestResponseHandler.parse(result);
    }

    @Override
    public AddNotesToBillingAccountReturn addNotesToBillingAccount(BisContext bisContext, CompositeObjectKey compositeObjectKey, String s, String[] strings, ObjectProperty[] objectProperties) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        AddNotesToBillingAccountRequestDto requestDto = AddNotesToBillingAccountRequestDto.builder()
                .aBisContext(bisContext)
                .aBillingAccountKey(compositeObjectKey)
                .aNoteType(s)
                .aNoteText(strings)
                .aNoteProperties(objectProperties)
                .build();

        Either<Throwables, Option<AddNotesToBillingAccountReturn>> result =
                bimxRestService.addNotesToBillingAccount(requestDto, new ParameterizedTypeReference<AddNotesToBillingAccountReturn>() {});

        return LmsRestResponseHandler.parseExcludingMultiple(result);
    }

    @Override
    public CreateBillingAccountReturn createBillingAccount(BisContext bisContext, CreateBillingAccountRequest createBillingAccountRequest) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound, MultipleExceptions {
        return null;
    }

    @Override
    public GetInstallmentBillingArrangementForBillingAccountReturn getInstallmentBillingArrangementForBillingAccount(BisContext bisContext, CompositeObjectKey compositeObjectKey, BooleanOpt booleanOpt) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public GetNonRecurringChargesForBillingAccountReturn getNonRecurringChargesForBillingAccount(BisContext bisContext, CompositeObjectKey compositeObjectKey, EiaDate eiaDate) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public CreateInstallmentBillingArrangementForBillingAccountReturn createInstallmentBillingArrangementForBillingAccount(BisContext bisContext, InstallmentBillingArrangementRequest installmentBillingArrangementRequest) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public ModifyInstallmentBillingArrangementForBillingAccountReturn modifyInstallmentBillingArrangementForBillingAccount(BisContext bisContext, InstallmentBillingArrangementRequest installmentBillingArrangementRequest) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public TerminateInstallmentBillingArrangementForBillingAccountReturn terminateInstallmentBillingArrangementForBillingAccount(BisContext bisContext, InstallmentBillingArrangementRequest installmentBillingArrangementRequest) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound {
        return null;
    }

    @Override
    public ModifyBillingAccountProfileReturn modifyBillingAccountProfile(BisContext bisContext, ModifyBillingAccountProfileRequest modifyBillingAccountProfileRequest) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound, MultipleExceptions {
        return null;
    }

    @Override
    public SelfTestReturn selfTest(BisContext bisContext) throws BusinessViolation, ObjectNotFound, InvalidData, AccessDenied, SystemFailure, NotImplemented, DataNotFound, MultipleExceptions {
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
