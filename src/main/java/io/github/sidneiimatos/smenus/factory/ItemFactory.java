package io.github.sidneiimatos.smenus.factory;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemFactory {
    public static ItemStack CriarItemPadrao(Material material, byte data, String nome, int quantidade, List<String> lore) {
        ItemStack item = new ItemStack(material, quantidade, (short)data);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(nome);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack CriarItemCabeca(String url, String nome, int quantidade, List<String> lore) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { url }).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = profileField = itemMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(itemMeta, profile);
        } catch (NoSuchFieldException|IllegalAccessException e) {
            e.printStackTrace();
        }
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(nome);
        item.setItemMeta((ItemMeta)itemMeta);
        item.setAmount(quantidade);
        return item;
    }
}