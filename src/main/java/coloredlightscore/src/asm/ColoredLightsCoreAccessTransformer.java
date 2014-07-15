package coloredlightscore.src.asm;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;

import java.io.IOException;

public class ColoredLightsCoreAccessTransformer extends AccessTransformer {

    public ColoredLightsCoreAccessTransformer() throws IOException {
        super("ColoredLightCore_at.cfg");
    }

}
