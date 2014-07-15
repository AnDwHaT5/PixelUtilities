package coloredlightscore.src.asm.transformer.core;

import coloredlightscore.src.asm.transformer.core.ASMUtils.ClassInfo;
import org.objectweb.asm.ClassWriter;

import static coloredlightscore.src.asm.transformer.core.ASMUtils.getClassInfo;
import static coloredlightscore.src.asm.transformer.core.ASMUtils.isAssignableFrom;

public class ExtendedClassWriter extends ClassWriter {

    public ExtendedClassWriter(int flags) {
        super(flags);
    }

    @Override
    protected String getCommonSuperClass(String type1, String type2) {
        ClassInfo cl1 = getClassInfo(type1);
        ClassInfo cl2 = getClassInfo(type2);

        // heaton84: If we can't get one side of the compare, just return the side we're working on
        if (cl1 == null)
            return type2;
        if (cl2 == null)
            return type1;

        if (isAssignableFrom(cl1, cl2)) {
            return type1;
        }
        if (isAssignableFrom(cl2, cl1)) {
            return type2;
        }
        if (cl1.isInterface() || cl2.isInterface()) {
            return "java/lang/Object";
        } else {
            do {
                cl1 = getClassInfo(cl1.superName());
            }
            while (!isAssignableFrom(cl1, cl2));
            return cl1.internalName();
        }
    }
}