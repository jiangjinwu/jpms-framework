package io.sited.page.tracking.baidu;

import io.sited.test.AppExtension;
import io.sited.test.Install;
import io.sited.test.MockApp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author chi
 */
@ExtendWith(AppExtension.class)
@Install({BaiduTrackingWebModule.class})
class BaiduTrackingWebModuleTest {
    @Inject
    MockApp app;

    @Test
    void configure() {
        assertNotNull(app);
    }
}