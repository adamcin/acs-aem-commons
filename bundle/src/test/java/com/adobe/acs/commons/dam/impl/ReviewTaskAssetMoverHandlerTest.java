package com.adobe.acs.commons.dam.impl;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import com.adobe.acs.commons.search.CloseableQueryBuilder;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReviewTaskAssetMoverHandlerTest {

    @Rule
    public SlingContext context = new SlingContext();

    @Mock
    private CloseableQueryBuilder mockQueryBuilder;

    @Mock
    private Scheduler mockScheduler;

    @Before
    public void setUp() throws Exception {
        context.registerService(CloseableQueryBuilder.class, mockQueryBuilder);
        context.registerService(Scheduler.class, mockScheduler);
    }

    @Test
    public void testConfig() {

        for (String defaultOption : Arrays.asList("new-asset", "new-version", "replace", "skip")) {
            ReviewTaskAssetMoverHandler handler = context.registerInjectActivateService(new ReviewTaskAssetMoverHandler(),
                    "conflict-resolution.default", defaultOption);
            assertEquals("getDefaultConflictResolution() should match input",
                    defaultOption, handler.getDefaultConflictResolution());
        }

    }
}
