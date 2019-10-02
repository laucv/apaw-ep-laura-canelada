package es.upm.miw.apaw_ep_themes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemResource.SYSTEM)
public class SystemResource {

    public static final String SYSTEM = "/system";
    public static final String VERSION = "/version";
    public static final String VERSION_BADGE = "/version-badge";

    @Value("${application.name}")
    private String applicationName;

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimestamp;

    @GetMapping(value = VERSION_BADGE, produces = {"image/svg+xml"})
    public byte[] generateBadge() { // http://localhost:8080/system/badge
        return new Badge().generateBadge("Heroku", "v" + buildVersion).getBytes();
    }

    @GetMapping(SystemResource.VERSION)
    public VersionDto readVersion() { // http://localhost:8080/system/version
        return new VersionDto(this.applicationName + "::" + this.buildVersion + "::" + this.buildTimestamp);
    }
}