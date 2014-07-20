package coloredlightscore.src.asm;


import coloredlightscore.src.asm.transformer.*;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.util.Map;

//@MCVersion("1.7.2")
//@SortingIndex(value=999)
public class ColoredLightsCoreLoadingPlugin implements IFMLLoadingPlugin {
    public static LaunchClassLoader CLASSLOADER;
    public static boolean MCP_ENVIRONMENT;

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
                TransformBlock.class.getName(),
                TransformWorld.class.getName(),
                TransformRenderBlocks.class.getName(),
                TransformTessellator.class.getName(),
                TransformChunkCache.class.getName(),
                TransformExtendedBlockStorage.class.getName(),
                TransformPlayerInstance.class.getName(),
                TransformEntityPlayerMP.class.getName(),
                TransformEntityRenderer.class.getName()
        };
    }

    @Override
    public String getModContainerClass() {
        return ColoredLightsCoreDummyContainer.class.getName();
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        MCP_ENVIRONMENT = !(Boolean) data.get("runtimeDeobfuscationEnabled");
        CLASSLOADER = (LaunchClassLoader) data.get("classLoader");
    }

    @Override
    public String getAccessTransformerClass() {
        return ColoredLightsCoreAccessTransformer.class.getName();
    }
}
