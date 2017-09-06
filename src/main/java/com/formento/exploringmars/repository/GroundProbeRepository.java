package com.formento.exploringmars.repository;

import com.formento.exploringmars.model.GroundProbe;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class GroundProbeRepository {

    private final Map<String, GroundProbe> groundProbes;

    public GroundProbeRepository() {
        this.groundProbes = new HashMap<>();
    }

    public GroundProbe save(GroundProbe groundProbe) {
        final String id = UUID.randomUUID().toString();
        groundProbe.setId(id);

        groundProbes.put(id, groundProbe);

        return groundProbe;
    }

    public Optional<GroundProbe> getById(final String id) {
        return groundProbes.
                entrySet().
                stream().
                filter(entry -> entry.getKey().equals(id)).
                map(Map.Entry::getValue).
                findAny();
    }

}
