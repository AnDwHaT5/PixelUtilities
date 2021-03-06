package coloredlightscore.src.asm.transformer;

import coloredlightscore.src.asm.transformer.core.HelperMethodTransformer;
import coloredlightscore.src.asm.transformer.core.NameMapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class TransformChunkCache extends HelperMethodTransformer {

    public TransformChunkCache() {
        super("net.minecraft.world.ChunkCache");
    }

    @Override
    protected Class<?> getHelperClass() {
        return coloredlightscore.src.helper.CLChunkCacheHelper.class;
    }

    @Override
    protected boolean transforms(ClassNode clazz, MethodNode method) {

        return NameMapper.getInstance().isMethod(method, super.className, "getLightBrightnessForSkyBlocks (IIII)I");
    }

    @Override
    protected boolean transform(ClassNode clazz, MethodNode method) {

        return NameMapper.getInstance().isMethod(method, super.className, "getLightBrightnessForSkyBlocks (IIII)I") && redefineMethod(clazz, method, "getLightBrightnessForSkyBlocks");

    }

}
