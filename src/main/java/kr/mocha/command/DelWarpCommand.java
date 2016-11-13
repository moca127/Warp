package kr.mocha.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.manager.WarpManager;

/**
 * Created by mocha on 16. 11. 13.
 */
public class DelWarpCommand extends Command {
    public WarpManager manager = new WarpManager();

    public DelWarpCommand() {
        super("워프삭제", "워프를 삭제합니다.", "/워프삭제 <워프이름>", new String[]{"delwarp", "warpdel", "dw"});
        this.setPermission("delWarp.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission(this.getPermission()))
            sender.sendMessage(TextFormat.RED + "명령어의 권한이 없습니다.");
        else {
            try {
                if (manager.isWarp(args[0])) {
                    manager.delWarp(args[0]);
                    sender.sendMessage(TextFormat.AQUA + "[ 알림 ] " + TextFormat.GRAY + "워프가 삭제되었습니다.");
                    return true;
                } else sender.sendMessage(TextFormat.AQUA + "[ 알림 ] " + TextFormat.GRAY + "워프가 아닙니다.");
            } catch (ArrayIndexOutOfBoundsException e) {
                sender.sendMessage(TextFormat.RED + this.getUsage());
            }
        }
            return false;
    }
}