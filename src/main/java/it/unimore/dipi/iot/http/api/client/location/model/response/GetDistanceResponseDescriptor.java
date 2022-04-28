package it.unimore.dipi.iot.http.api.client.location.model.response;

import it.unimore.dipi.iot.http.api.client.location.model.TerminalDistance;

public class GetDistanceResponseDescriptor {
    private TerminalDistance terminalDistance;

    public TerminalDistance getTerminalDistance() {
        return terminalDistance;
    }

    public void setTerminalDistance(TerminalDistance terminalDistance) {
        this.terminalDistance = terminalDistance;
    }
}
