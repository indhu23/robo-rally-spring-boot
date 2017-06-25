package game.Entity;

import java.util.List;

/**
 * Created by Indhu on 6/11/2017.
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
