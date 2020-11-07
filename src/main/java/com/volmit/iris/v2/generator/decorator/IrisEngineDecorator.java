package com.volmit.iris.v2.generator.decorator;

import com.volmit.iris.object.DecorationPart;
import com.volmit.iris.object.IrisBiome;
import com.volmit.iris.object.IrisDecorator;
import com.volmit.iris.util.RNG;
import com.volmit.iris.v2.generator.IrisEngine;
import com.volmit.iris.v2.scaffold.engine.Engine;
import com.volmit.iris.v2.scaffold.engine.EngineAssignedComponent;
import com.volmit.iris.v2.scaffold.engine.EngineDecorator;
import com.volmit.iris.v2.scaffold.hunk.Hunk;
import lombok.Getter;
import org.bukkit.block.data.BlockData;

public abstract class IrisEngineDecorator extends EngineAssignedComponent implements EngineDecorator {

    @Getter
    private final RNG rng;

    @Getter
    private final DecorationPart part;

    public IrisEngineDecorator(Engine engine, String name, DecorationPart part) {
        super(engine, name + " Decorator");
        this.part = part;
        this.rng = new RNG(getSeed() + 29356788 - (part.ordinal() * 10439677));
    }

    protected IrisDecorator getDecorator(IrisBiome biome, double realX, double realZ)
    {
        for(IrisDecorator i : biome.getDecorators())
        {
            if(i.getPartOf().equals(part))
            {
                if(i.getBlockData(biome, rng, realX, realZ, getData()) != null)
                {
                    return i;
                }
            }
        }

        return null;
    }
}