
package com.androwep.androweptutorials.util.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompileResponse {

    @SerializedName("output")
    @Expose
    private String output;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("memory")
    @Expose
    private String memory;
    @SerializedName("cpuTime")
    @Expose
    private String cpuTime;
    @SerializedName("error")
    @Expose
    private String error;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(String cpuTime) {
        this.cpuTime = cpuTime;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
