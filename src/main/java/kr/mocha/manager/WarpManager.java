package kr.mocha.manager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import kr.mocha.Warp;

/**
 * @author {mocha, Angelless}
 * 워프의 생성, 삭제, 확인 등 여러가지 기능을 하는 클래스입니다.
 */
public class WarpManager {
    public Warp warp = Warp.getInstance();;

    /**
     * 플레이어의 좌표정보를 이용하여 워프를 생성합니다.
     * @param warp
     * @param player
     * @return
     */
    public boolean addWarp(String warp, Player player){
        return addWarp(warp, player.getX(),player.getY(),player.getZ(),player.getLevel());
    }

    /**
     * 워프를 생성합니다.
     * @param warp
     * @param x
     * @param y
     * @param z
     * @param level
     * @return
     */
    public boolean addWarp(String warp, double x, double y, double z, Level level){
        try{
            if(!warp.contains(".")){
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();

                map.put("x", x);
                map.put("y", y);
                map.put("z", z);
                map.put("level", level.getFolderName());

                this.warp.warps.set(warp, map);
                this.warp.save();
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    /**
     * 워프를 삭제합니다.
     * @param warp
     * @return
     */
    public boolean delWarp(String warp){
        try{
            this.warp.warps.remove(warp);
            this.warp.save();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * 워프의 존재여부를 반환합니다.
     * @param warp
     * @return
     */
    public boolean isWarp(String warp){
        return this.warp.warps.exists(warp);
    }

    /**
     * 워프의 목록을 {@link ArrayList} 로 반환합니다.
     * @return
     */
    public Set<String> getList(){
        Set<String> list = warp.warps.getKeys();
        list.removeIf(s -> s.contains("."));
        return list;
    }

    /**
     * player를 워프시키는 메서드입니다.
     * @param warp
     * @param player
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean warp(String warp, Player player){
        try{
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)this.warp.warps.get(warp);
            double x,y,z;
            x = toDouble(map.get("x"));
            y = toDouble(map.get("y"));
            z = toDouble(map.get("z"));
            player.teleport(new Position(x,y,z,this.warp.getServer().getLevelByName(map.get("level").toString())));
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Object 형태의 데이터를 double 로 변환시킵니다. 내부적으로만 사용합니다.
     * @param o
     * @return
     */
    private double toDouble(Object o){
        if(o instanceof Integer) return ((Integer)o).doubleValue();
        if(o instanceof Double) return ((Double)o).doubleValue();
        if(o instanceof String) return Double.parseDouble(o.toString());
        return 0.0D;
    }
}