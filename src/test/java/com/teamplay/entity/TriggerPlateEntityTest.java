package com.teamplay.entity;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TriggerPlateEntityTest {

    @Test
    public void shouldAlterActiveStateWhenTriggered() {
        TriggerPlateEntity triggerPlateEntity = new TriggerPlateEntity();
        assertFalse(triggerPlateEntity.isActive());
        triggerPlateEntity.trigger();
        assertTrue(triggerPlateEntity.isActive());
        triggerPlateEntity.trigger();
        assertFalse(triggerPlateEntity.isActive());
    }
}
