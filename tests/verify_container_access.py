from pathlib import Path

root = Path(__file__).resolve().parents[1]
handler = (root / "src/main/java/emanondev/itemedit/gui/GuiHandler.java").read_text()
helper = root / "src/main/java/emanondev/itemedit/utility/InventoryHolderAccess.java"
assert helper.exists()
assert "InventoryHolderAccess.getHolder(topInventory)" in handler
assert ".getHolder()" not in handler
assert " instanceof Gui " not in handler, "Java 16 pattern matching is not Java 8 compatible"
print("PASS: ItemEdit Java 8 compatible cached holder access")
