package game.Entity;

import java.util.List;

/**
 * Holds the list of registers
 */
public class SendRegisterInputWrapper {
    public SendRegisterInputWrapper(List<String> registers) {
        this.registers = registers;
    }

    public List<String> getRegisters() {
        return registers;
    }

    public void setRegisters(List<String> registers) {
        this.registers = registers;
    }

    private List<String> registers;
}
