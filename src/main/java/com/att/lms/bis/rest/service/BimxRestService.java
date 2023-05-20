package com.att.lms.bis.rest.service;

import com.att.lms.bis.common.CommonApi;
import com.att.lms.bis.common.config.settings.IMappedHttpSettings;
import com.att.lms.bis.common.dto.Throwables;
import com.att.lms.bis.dto.bimx.AddNotesToBillingAccountRequestDto;
import com.att.lms.bis.dto.bimx.PingRequestDto;
import com.att.lms.bis.rest.config.settings.RestConstants;
import com.att.lms.bis.rest.dao.BimxRestDao;
import com.sbc.eia.idl.bim_types.AddNotesToBillingAccountReturn;
import com.sbc.eia.idl.bimx.PingReturn;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
public class BimxRestService {

    BimxRestDao bimxRestDao;
    IMappedHttpSettings bisRestEndpointSettings;

    public BimxRestService(BimxRestDao bimxRestDao, IMappedHttpSettings bisRestEndpointSettings) {
        this.bimxRestDao = bimxRestDao;
        this.bisRestEndpointSettings = bisRestEndpointSettings;
    }

    public Either<Throwables, Option<PingReturn>> ping(
            final PingRequestDto requestDto,
            final ParameterizedTypeReference<PingReturn> type) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(CommonApi.Headers.APPLICATION_ID, "BIS");
        headers.add(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString());
        headers.setContentType(APPLICATION_JSON);

        return bimxRestDao.ping(bisRestEndpointSettings.getRestEndpoints()
                        .get(RestConstants.BIMX),
                requestDto,
                type,
                headers);
    }

    public Either<Throwables, Option<AddNotesToBillingAccountReturn>> addNotesToBillingAccount(
            final AddNotesToBillingAccountRequestDto requestDto,
            final ParameterizedTypeReference<AddNotesToBillingAccountReturn> type) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(CommonApi.Headers.APPLICATION_ID, "BIS");
        headers.add(CommonApi.Headers.CORRELATION_ID, UUID.randomUUID().toString());
        headers.setContentType(APPLICATION_JSON);

        return bimxRestDao.addNotesToBillingAccount(bisRestEndpointSettings.getRestEndpoints()
                        .get(RestConstants.BIMX),
                requestDto,
                type,
                headers);
    }
}
