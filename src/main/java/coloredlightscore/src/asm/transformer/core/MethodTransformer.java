package coloredlightscore.src.asm.transformer.core;

import cpw.mods.fml.common.FMLLog;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public abstract class MethodTransformer extends SelectiveTransformer {
    @Override
    protected boolean transform(ClassNode clazz, String className) {
        // 03-06-2014 heaton84: Made so that it will transform more than one method
        boolean result = preTransformClass(clazz);

        for (MethodNode method : clazz.methods) {
            if (transforms(clazz, method)) {
                FMLLog.info("Transforming method " + method.name);
                result |= transform(clazz, method);
            }
        }

        result |= postTransformClass(clazz);

        return result;
    }

    protected abstract boolean transforms(ClassNode clazz, MethodNode method);

    protected abstract boolean transform(ClassNode clazz, MethodNode method);

    protected boolean preTransformClass(ClassNode clazz) {
        return false;
    }

    protected boolean postTransformClass(ClassNode clazz) {
        return false;
    }

}