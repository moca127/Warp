package kr.mocha;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import kr.mocha.command.AddWarpCommand;
import kr.mocha.command.DelWarpCommand;
import kr.mocha.command.ListWarpCommand;
import kr.mocha.command.WarpCommand;
import kr.mocha.task.AutoSave;

import java.io.File;

/**
 * Created by mocha on 16. 11. 13.
 */
public class Warp extends PluginBase implements Listener{
    public static Warp instance;
    public Config warps;

    @Override
    public void onEnable() {
        instance = this;
        getDataFolder().mkdirs();
        warps = new Config(getDataFolder()+ File.separator+"warps.yml", Config.YAML);
        this.getServer().getScheduler().scheduleDelayedRepeatingTask(new AutoSave(),0,6000);
        this.getServer().getCommandMap().register("워프추가" ,new AddWarpCommand());
        this.getServer().getCommandMap().register("워프삭제", new DelWarpCommand());
        this.getServer().getCommandMap().register("워프목록", new ListWarpCommand());
        this.getServer().getCommandMap().register("워프", new WarpCommand());
        super.onEnable();
    }
    @Override
    public void onDisable() {
        this.save();
        super.onDisable();
    }

    /*utils*/
    public void save(){
        warps.save();
    }
    public static Warp getInstance(){
        return instance;
    }
}
