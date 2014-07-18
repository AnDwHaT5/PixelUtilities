package coloredlightscore.src.asm.transformer;

import coloredlightscore.src.asm.transformer.core.ASMUtils;
import coloredlightscore.src.asm.transformer.core.SingleMethodTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class TransformTessellator extends SingleMethodTransformer {


    public TransformTessellator() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected String getMcpMethod() {
        return "setBrightness (I)V";
    }

    @Override
    protected String getClassName() {
        return "net.minecraft.client.renderer.Tessellator";
    }

    @Override
    protected boolean transform(ClassNode clazz, MethodNode method) {

        /* 
         * This ASM was taken from the mockup "CLTesselatorHelper.setBrightness", as compared to Tessellator.class
         * We want to insert the lines starting with '+':
         * 
         * 0  aload_0 [this]
         * 1  iconst_1
         * 2  putfield kovukore.coloredlights.src.helper.CLTesselatorHelper.hasBrightness : boolean [20]
         * 5  aload_0 [this]
         * 6  iload_1 [par1]
         * + 7  ldc <Integer 15728880> [22]
         * + 9  iand
         * 10  putfield kovukore.coloredlights.src.helper.CLTesselatorHelper.brightness : int [23]
         * 13  return
         * 
         */

        AbstractInsnNode putBrightness = ASMUtils.findLastOpcode(method, Opcodes.PUTFIELD);
        InsnList andOperation = new InsnList();

        andOperation.add(new LdcInsnNode(15728880));
        andOperation.add(new InsnNode(Opcodes.IAND));

        method.instructions.insertBefore(putBrightness, andOperation);

        return true;
    }

    @Override
    protected boolean transforms(String className) {
        return className.equals(getClassName());
    }

}
