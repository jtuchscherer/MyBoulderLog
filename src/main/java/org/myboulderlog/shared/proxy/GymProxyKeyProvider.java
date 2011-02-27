package org.myboulderlog.shared.proxy;

import com.google.gwt.view.client.ProvidesKey;

public class GymProxyKeyProvider implements ProvidesKey<GymProxy> {
    public Object getKey(GymProxy item) {
        return (item == null) ? null : item.getId();
    }
}
