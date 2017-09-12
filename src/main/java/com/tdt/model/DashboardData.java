package com.tdt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DashboardData implements java.io.Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -3892843530380359390L;
    private final int candidates;
    private final int notes;
    private final int reminders;
}
