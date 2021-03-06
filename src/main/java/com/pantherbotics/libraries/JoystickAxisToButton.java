package com.pantherbotics.libraries;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * This class takes a Joystick object and an axis id and translates it into JoystickButton-like methods.
 * This methods of this class are:
 *   .whileHeld(Command command) - Schedules a command to be run while the joystick axis is held down
 *   .whenPressed(Command command) - Schedules a command to be run once the joystick axis is pressed
 *   .whenReleased(Command command) - Schedules a command to be run once the joystick axis is released
 *   .get() - Returns a boolean for joystick isPressed
 */
@SuppressWarnings("unused")
public class JoystickAxisToButton {
    private final Joystick joystick;
    private final int axis;
    private boolean wasHeld = false;
    public JoystickAxisToButton(Joystick joystick, int axis) {
        this.joystick = joystick;
        this.axis = axis;

        //If newly pressed
        //If continued press
        //If was pressed but no longer (released)
        Notifier loop = new Notifier(() -> {
            boolean pressed = joystick.getRawAxis(axis) >= 0.1;

            if (!wasHeld && pressed) { //If newly pressed
                runHeldCommands();
                runPressedCommands();
            } else if (wasHeld && pressed) { //If continued press
                runHeldCommands();
            } else if (wasHeld) { //If was pressed but no longer (released)
                cancelHeldCommands();
                runReleasedCommands();
            }

            wasHeld = pressed;
        });
        loop.startPeriodic(0.02);
    }

    List<Command> heldCommands = new ArrayList<>();
    public void whileHeld(Command command) {
        heldCommands.add(command);
    }
    private void runHeldCommands() {
        for (Command held : heldCommands) {
            held.schedule(true);
        }
    }
    private void cancelHeldCommands() {
        for (Command held : heldCommands) {
            held.cancel();
        }
    }

    List<Command> pressedCommands = new ArrayList<>();
    public void whenPressed(Command command) {
        pressedCommands.add(command);
    }
    private void runPressedCommands() {
        for (Command pressed : pressedCommands) {
            pressed.schedule();
        }
    }

    List<Command> releasedCommands = new ArrayList<>();
    public void whenReleased(Command command) {
        releasedCommands.add(command);
    }
    private void runReleasedCommands() {
        for (Command released : releasedCommands) {
            released.schedule();
        }
    }

    public boolean get() {
        return joystick.getRawAxis(axis) >= 0.1;
    }
}