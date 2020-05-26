package com.android.covid.home.data;

import com.google.gson.annotations.SerializedName;

/**
 * https://corona-virus-stats.herokuapp.com/api/v1/cases/general-stats
 */
public class CovidGlobalStats {
    @SerializedName("total_cases")
    public String totalCases;

    @SerializedName("status")
    public String success;

    @SerializedName("recovery_cases")
    public String recoveryCases;

    @SerializedName("death_cases")
    public String deathCases;

    @SerializedName("last_update")
    public String lastUpdate;

    @SerializedName("currently_infected")
    public String currentlyInfected;

    @SerializedName("cases_with_outcome")
    public String casesWithOutcome;

    @SerializedName("mild_condition_active_cases")
    public String mildConditionActiveCases;

    @SerializedName("critical_condition_active_cases")
    public String criticalConditionActiveCases;

    @SerializedName("recovered_closed_cases")
    public String recoveredClosedCases;

    @SerializedName("death_closed_cases")
    public String deathClosedCases;

    @SerializedName("closed_cases_recovered_percentage")
    public String closedCasesRecoveredPercentage;

    @SerializedName("closed_cases_death_percentage")
    public String closedCasesDeathPercentage;

    @SerializedName("active_cases_mild_percentage")
    public String activeCasesMildPercentage;

    @SerializedName("active_cases_critical_percentage")
    public String activeCasesCriticalPercentage;

    @SerializedName("general_death_rate")
    public String generalDeathRate;

}

