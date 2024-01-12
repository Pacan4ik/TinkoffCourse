package edu.hw11;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("MagicNumber")
public class FibCodeAppender implements ByteCodeAppender {
    @Override
    public @NotNull Size apply(
        @NotNull MethodVisitor methodVisitor,
        Implementation.@NotNull Context context,
        @NotNull MethodDescription methodDescription
    ) {
        Label main = new Label();
        Label resReturn = new Label();
        Label whileStart = new Label();

        // n < 1?
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, main);
        methodVisitor.visitInsn(Opcodes.ICONST_M1);
        methodVisitor.visitInsn(Opcodes.IRETURN);

        // main loop
        methodVisitor.visitLabel(main);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 1); // a = 1
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 2); // b = 1
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 3); // res = 1
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 4); // i = 2
        methodVisitor.visitLabel(whileStart);

        methodVisitor.visitFrame(
            Opcodes.F_FULL,
            5,
            new Object[] {Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER},
            0,
            null
        );

        methodVisitor.visitVarInsn(Opcodes.ILOAD, 4);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, resReturn);

        // loop body
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
        methodVisitor.visitInsn(Opcodes.IADD);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 3); // res = a + b
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 1); // a = b
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 3);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 2); // b = res
        methodVisitor.visitIincInsn(4, 1); // i++
        methodVisitor.visitJumpInsn(Opcodes.GOTO, whileStart);

        // return
        methodVisitor.visitLabel(resReturn);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 3);
        methodVisitor.visitInsn(Opcodes.IRETURN);

        return new Size(2, 5);

    }
}
