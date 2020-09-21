package com.ssaw.GlobalManagement.util;

/**
 * 表名工具类
 * 数据库表名常量工具类字典
 * @type: util
 * @version: v1.0
 * @author: plum
 * @date: 2020/09/03
 */
public class SysTableNameListUtil {
    public static final String F = "fund";
    public static final String B = "brokers";
    public static final String S = "seate";
    public static final String VR = "varietiesRate";
    public static final String SE = "securities";
    public static final String BO = "bond";
    public static final String ST = "stock";
    public static final String A = "account";
    public static final String TD = "transactionData";
    public static final String VS = "valueStatistics";
    public static final String SCP = "securitiesClosedPay";
    public static final String CCP = "cashClosedPay";
    public static final String M = "market";
    public static final String TT = "taTransaction";
    public static final String SI = "securitiesInventory";
    public static final String CI = "cashInventory";
    public static final String TI = "taInventory";
    public static final String T = "trustee";
    public static final String MA = "manager";
    public static final String BT = "bankTreasurer";
    public static final String ED = "equityData";
    public static final String CCPI = "cashClosedPayInventory";
    public static final String SCPI = "securitiesClosedPayInventory";
    public static final String DE = "deposit";
    public static final String TM = "transferMoney";
    public static final String UI = "userInfo";
    public static final String R = "role";
    public static final String SL = "ssawLog";

    /**
     * 获取表名简写
     * @param tableName 需要获取简写的表名
     * @return 表名简写值
     */
    public static String getTableNameAbbr(String tableName){
        String tNa = null;
        switch (tableName){
            case A:
                tNa="A";
                break;
            case B:
                tNa="B";
                break;
            case BO:
                tNa="BO";
                break;
            case BT:
                tNa="BT";
                break;
            case CCP:
                tNa="CCP";
                break;
            case CCPI:
                tNa="CCPI";
                break;
            case CI:
                tNa="CI";
                break;
            case DE:
                tNa="DE";
                break;
            case ED:
                tNa="ED";
                break;
            case F:
                tNa="F";
                break;
            case M:
                tNa="M";
                break;
            case MA:
                tNa="MA";
                break;
            case R:
                tNa="R";
                break;
            case S:
                tNa="S";
                break;
            case SCP:
                tNa="SCP";
                break;
            case SCPI:
                tNa="SCPI";
                break;
            case SE:
                tNa="SE";
                break;
            case SI:
                tNa="SI";
                break;
            case ST:
                tNa="ST";
                break;
            case T:
                tNa="T";
                break;
            case TD:
                tNa="TD";
                break;
            case TI:
                tNa="TI";
                break;
            case TM:
                tNa="TM";
                break;
            case TT:
                tNa="TT";
                break;
            case UI:
                tNa="UI";
                break;
            case VR:
                tNa="VR";
                break;
            case VS:
                tNa="VS";
                break;
            case SL:
                tNa="SL";
                break;
        }
        return tNa;
    }
}
