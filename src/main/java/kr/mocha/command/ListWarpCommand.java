package kr.mocha.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.manager.WarpManager;

/**
 * Created by mocha on 16. 11. 13.
 */
public class ListWarpCommand extends Command{
    public WarpManager manager = new WarpManager();

    public ListWarpCommand(){
        super("워프목록", "워프의 목록을 확인합니다.", "/워프목록", new String[]{"listwarp", "warplist", "lw"});
        this.setPermission("listWarp.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!sender.hasPermission(this.getPermission()))
            sender.sendMessage(TextFormat.RED+"명령어의 권한이 없습니다.");
        else{
            String result = "";

            for(String s:manager.getList())
                result += s+", ";

            sender.sendMessage("=== 워프목록 ("+manager.getList().size()+")===");
            sender.sendMessage(TextFormat.GREEN+result);
            return true;
        }
        return false;
    }
}
