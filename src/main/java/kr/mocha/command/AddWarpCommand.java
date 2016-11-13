package kr.mocha.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.manager.WarpManager;

/**
 * Created by mocha on 16. 11. 13.
 */
public class AddWarpCommand extends Command{
    public WarpManager manager = new WarpManager();

    public AddWarpCommand(){
        super("워프추가", "워프를 추가합니다.", "/워프추가 <워프이름>", new String[]{"addwarp", "warpadd", "aw"});
        this.setPermission("addWarp.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!sender.hasPermission(this.getPermission()))
            sender.sendMessage(TextFormat.RED+"명령어의 권한이 없습니다.");
        else{
            try {
                manager.addWarp(args[0], (Player)sender);
                sender.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"워프가 추가되었습니다.");
                return true;
            }catch (ArrayIndexOutOfBoundsException e){
                sender.sendMessage(TextFormat.RED+this.getUsage());
            }catch (ClassCastException e){
                sender.sendMessage(TextFormat.RED+"플레이어만 사용가능합니다.");
            }
        }
        return false;
    }
}
