package data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public final class LocalData {

    private final @NotNull File file;
    private @Nullable Entity entity;

    public LocalData(@NotNull String rootFolder) {
        @NotNull File directory = new File(rootFolder);
        this.file = new File(directory, "LocalData.bin");
        if (!directory.exists()) {
            throw new RuntimeException("Folder not exist");
        }
        createBin();
    }

    private void createBin() {
        try {
            if (file.createNewFile()) {
                System.out.println("LocalData created on " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create File: " + file.getAbsolutePath());
        }
    }

    public void writeEntity(@NotNull Entity entity) {
        this.entity = entity;
        try (ObjectOutputStream obs = new ObjectOutputStream(new FileOutputStream(file))) {
            obs.writeObject(entity);
        } catch (IOException e) {
            System.err.println("Failed to write entity: " + e.getMessage());
        }
    }

    public @Nullable Entity getEntity(long entityId) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    this.entity = (Entity) ois.readObject();
                    if (entity.getId() == entityId) {
                        return entity;
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to read entity: " + e.getMessage());
        }
        return null;
    }

}


