package kr.mocha.task;

import cn.nukkit.scheduler.Task;
import kr.mocha.Warp;

/**
 * Created by mocha on 16. 11. 13.
 */
public class AutoSave extends Task{
    @Override
    public void onRun(int tick) {
        Warp.getInstance().save();
    }
}
