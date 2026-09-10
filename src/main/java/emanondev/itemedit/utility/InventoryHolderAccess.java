package emanondev.itemedit.utility;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.lang.reflect.Method;

/** Retrieves inventory holders without block-state snapshots on Paper. */
public final class InventoryHolderAccess {
    private static final Method NON_SNAPSHOT_GET_HOLDER = findNonSnapshotMethod();

    private InventoryHolderAccess() {
    }

    public static InventoryHolder getHolder(Inventory inventory) {
        if (inventory == null) {
            return null;
        }
        if (NON_SNAPSHOT_GET_HOLDER != null) {
            try {
                return (InventoryHolder) NON_SNAPSHOT_GET_HOLDER.invoke(inventory, false);
            } catch (ReflectiveOperationException ignored) {
                // Continue with the Spigot-compatible API.
            }
        }
        return inventory.getHolder();
    }

    private static Method findNonSnapshotMethod() {
        try {
            return Inventory.class.getMethod("getHolder", boolean.class);
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }
}
