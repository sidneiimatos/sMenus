package io.github.sidneiimatos.smenus.inventory;

import com.avaje.ebeaninternal.server.persist.BindValues;
import com.sun.org.apache.xerces.internal.xs.StringList;
import io.github.sidneiimatos.smenus.SMenus;
import io.github.sidneiimatos.smenus.factory.ItemFactory;
import io.github.sidneiimatos.smenus.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class MenuInventory {

    //private static final List<String> LORE_ITEM = SMenus.getInstance().getConfig().getStringList("ITENS.NAME.DISPLAY.DESCRIPTION");
    private Inventory inv;
    public MenuInventory(Player p) {
        p.closeInventory();
        Inventory inv = Bukkit.createInventory(null, SMenus.config.getConfig().getInt("menu.tamanho"), SMenus.config.getConfig().getString("menu.nome"));
        for (String itens : SMenus.config.getConfig().getConfigurationSection("menu.itens").getKeys(false)) {
            List<String> lore = new ArrayList<>();
            for (String a : SMenus.config.getConfig().getStringList("menu.itens."+itens+".display.description")) {
                lore.add(a.replace("&", "§"));
            }
            inv.setItem(SMenus.config.getConfig().getInt("menu.itens."+itens+".inventory-slot"),
                    new ItemBuilder(Material.getMaterial(SMenus.config.getConfig().getString("menu.itens."+itens+".display.material")),
                            SMenus.config.getConfig().getInt("menu.itens."+itens+".display.quantity"),
                            (short)SMenus.config.getConfig().getInt("menu.itens."+itens+".display.data"))
                            .setName(SMenus.config.getConfig().getString("menu.itens."+itens+".display.name").replace("&", "§"))
                            .setAmount(SMenus.config.getConfig().getInt("menu.itens."+itens+".display.quantity"))
                            .setLore(lore)
                            .build());
        }


        p.openInventory(inv);
    }

    public void getInventory() {

    }

}
