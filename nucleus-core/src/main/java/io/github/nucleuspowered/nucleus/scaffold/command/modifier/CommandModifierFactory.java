/*
 * This file is part of Nucleus, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package io.github.nucleuspowered.nucleus.scaffold.command.modifier;

import io.github.nucleuspowered.nucleus.scaffold.command.control.CommandControl;
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.annotation.NonnullByDefault;

import java.util.function.Function;

@NonnullByDefault
public abstract class CommandModifierFactory implements CatalogType, Function<CommandControl, ICommandModifier> {

    public static class Simple extends CommandModifierFactory {

        private final String id;
        private final String name;
        private final Function<CommandControl, ICommandModifier> modifierFunction;

        public Simple(ICommandModifier modifier) {
            this(modifier.getId(), modifier.getName(), control -> modifier);
        }

        public Simple(String id, String name, Function<CommandControl, ICommandModifier> modifierFunction) {
            this.id = id;
            this.name = name;
            this.modifierFunction = modifierFunction;
        }

        @Override public String getId() {
            return this.id;
        }

        @Override public String getName() {
            return this.name;
        }

        @Override public ICommandModifier apply(CommandControl control) {
            return this.modifierFunction.apply(control);
        }
    }
}
