package com.att.lms.bis.rest.config.settings;

import com.att.lms.bis.common.config.settings.IMappedHttpSettings;
import com.att.lms.bis.common.config.settings.RestEndpointSettings;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@ConfigurationProperties(prefix = "bis")
public class BisRestEndpointSettings implements IMappedHttpSettings {
  public static final String BIS_REST_ENDPOINT_PROPERTIES_PREFIX =
      RestConstants.BIS_ENDPOINT_PROPERTIES_PREFIX;

  private Map<String, RestEndpointSettings> restEndpoints = new HashMap<>();
}
