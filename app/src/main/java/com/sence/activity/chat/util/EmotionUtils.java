
package com.sence.activity.chat.util;



import com.sence.R;

import java.util.LinkedHashMap;

/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 * 表情加载类,可自己添加多种表情，分别建立不同的map存放和不同的标志符即可
 */
public class EmotionUtils {

    /**
     * key-表情文字;
     * value-表情图片资源
     */
    public static LinkedHashMap<String, Integer> EMPTY_GIF_MAP = new LinkedHashMap<>();
    public static LinkedHashMap<String, Integer> EMOTION_STATIC_MAP;


    static {
//        EMPTY_GIF_MAP = new LinkedHashMap<>();
//
//        EMPTY_GIF_MAP.put("[微笑]", R.drawable.emotion_weixiao_gif);
//        EMPTY_GIF_MAP.put("[撇嘴]", R.drawable.emotion_biezui_gif);
//        EMPTY_GIF_MAP.put("[色]", R.drawable.emotion_se_gif);
//        EMPTY_GIF_MAP.put("[发呆]", R.drawable.emotion_fadai_gif);
//        EMPTY_GIF_MAP.put("[得意]", R.drawable.emotion_deyi_gif);
//        EMPTY_GIF_MAP.put("[流泪]", R.drawable.emotion_liulei_gif);
//        EMPTY_GIF_MAP.put("[害羞]", R.drawable.emotion_haixiu_gif);
//        EMPTY_GIF_MAP.put("[闭嘴]", R.drawable.emotion_bizui_gif);
//        EMPTY_GIF_MAP.put("[睡]", R.drawable.emotion_shui_gif);
//        EMPTY_GIF_MAP.put("[大哭]", R.drawable.emotion_daku_gif);
//        EMPTY_GIF_MAP.put("[尴尬]", R.drawable.emotion_ganga_gif);
//        EMPTY_GIF_MAP.put("[发怒]", R.drawable.emotion_fanu_gif);
//        EMPTY_GIF_MAP.put("[调皮]", R.drawable.emotion_tiaopi_gif);
//        EMPTY_GIF_MAP.put("[呲牙]", R.drawable.emotion_ciya_gif);
//        EMPTY_GIF_MAP.put("[惊讶]", R.drawable.emotion_jingya_gif);
//        EMPTY_GIF_MAP.put("[难过]", R.drawable.emotion_nanguo_gif);
//        EMPTY_GIF_MAP.put("[酷]", R.drawable.emotion_ku_gif);
//        EMPTY_GIF_MAP.put("[冷汗]", R.drawable.emotion_lenghan_gif);
//        EMPTY_GIF_MAP.put("[抓狂]", R.drawable.emotion_zhuakuang_gif);
//        EMPTY_GIF_MAP.put("[吐]", R.drawable.emotion_tu_gif);
//        EMPTY_GIF_MAP.put("[偷笑]", R.drawable.emotion_touxiao_gif);
//        EMPTY_GIF_MAP.put("[可爱]", R.drawable.emotion_keai_gif);
//        EMPTY_GIF_MAP.put("[白眼]", R.drawable.emotion_baiyan_gif);
//        EMPTY_GIF_MAP.put("[傲慢]", R.drawable.emotion_aoman_gif);
//        EMPTY_GIF_MAP.put("[饥饿]", R.drawable.emotion_jie_gif);
//        EMPTY_GIF_MAP.put("[困]", R.drawable.emotion_kun_gif);
//        EMPTY_GIF_MAP.put("[惊恐]", R.drawable.emotion_jingkong_gif);
//        EMPTY_GIF_MAP.put("[流汗]", R.drawable.emotion_liuhan_gif);
//        EMPTY_GIF_MAP.put("[憨笑]", R.drawable.emotion_hanxiao_gif);
//        EMPTY_GIF_MAP.put("[大兵]", R.drawable.emotion_dabing_gif);
//        EMPTY_GIF_MAP.put("[奋斗]", R.drawable.emotion_fendou_gif);
//        EMPTY_GIF_MAP.put("[咒骂]", R.drawable.emotion_zouma_gif);
//        EMPTY_GIF_MAP.put("[疑问]", R.drawable.emotion_yiwen_gif);
//        EMPTY_GIF_MAP.put("[嘘]", R.drawable.emotion_xu_gif);
//        EMPTY_GIF_MAP.put("[晕]", R.drawable.emotion_yun_gif);
//        EMPTY_GIF_MAP.put("[折磨]", R.drawable.emotion_fakuang_gif);
//        EMPTY_GIF_MAP.put("[衰]", R.drawable.emotion_shuai_gif);
//        EMPTY_GIF_MAP.put("[骷髅]", R.drawable.emotion_kulou_gif);
//        EMPTY_GIF_MAP.put("[敲打]", R.drawable.emotion_qiaoda_gif);
//        EMPTY_GIF_MAP.put("[再见]", R.drawable.emotion_zaijian_gif);
//        EMPTY_GIF_MAP.put("[擦汗]", R.drawable.emotion_cahan_gif);
//        EMPTY_GIF_MAP.put("[抠鼻]", R.drawable.emotion_koubi_gif);
//        EMPTY_GIF_MAP.put("[鼓掌]", R.drawable.emotion_guzhang_gif);
//        EMPTY_GIF_MAP.put("[糗大了]", R.drawable.emotion_qiudale_gif);
//        EMPTY_GIF_MAP.put("[坏笑]", R.drawable.emotion_huaixiao_gif);
//        EMPTY_GIF_MAP.put("[左哼哼]", R.drawable.emotion_zuohengheng_gif);
//        EMPTY_GIF_MAP.put("[右哼哼]", R.drawable.emotion_youhengheng_gif);
//        EMPTY_GIF_MAP.put("[哈欠]", R.drawable.emotion_haqian_gif);
//        EMPTY_GIF_MAP.put("[鄙视]", R.drawable.emotion_bishi_gif);
//        EMPTY_GIF_MAP.put("[委屈]", R.drawable.emotion_weiqu_gif);
//        EMPTY_GIF_MAP.put("[快哭了]", R.drawable.emotion_kuaikule_gif);
//        EMPTY_GIF_MAP.put("[阴险]", R.drawable.emotion_yingxian_gif);
//        EMPTY_GIF_MAP.put("[亲亲]", R.drawable.emotion_qinqin_gif);
//        EMPTY_GIF_MAP.put("[吓]", R.drawable.emotion_xia_gif);
//        EMPTY_GIF_MAP.put("[可怜]", R.drawable.emotion_kelian_gif);
//        EMPTY_GIF_MAP.put("[菜刀]", R.drawable.emotion_caidao_gif);
//        EMPTY_GIF_MAP.put("[西瓜]", R.drawable.emotion_xigua_gif);
//        EMPTY_GIF_MAP.put("[啤酒]", R.drawable.emotion_pijiu_gif);
//        EMPTY_GIF_MAP.put("[篮球]", R.drawable.emotion_lanqiu_gif);
//        EMPTY_GIF_MAP.put("[乒乓]", R.drawable.emotion_pingpang_gif);
//        EMPTY_GIF_MAP.put("[咖啡]", R.drawable.emotion_kafei_gif);
//        EMPTY_GIF_MAP.put("[饭]", R.drawable.emotion_fan_gif);
//        EMPTY_GIF_MAP.put("[猪头]", R.drawable.emotion_zhutou_gif);
//        EMPTY_GIF_MAP.put("[玫瑰]", R.drawable.emotion_meigui_gif);
//        EMPTY_GIF_MAP.put("[凋谢]", R.drawable.emotion_diaoxie_gif);
//        EMPTY_GIF_MAP.put("[示爱]", R.drawable.emotion_shiai_gif);
//        EMPTY_GIF_MAP.put("[爱心]", R.drawable.emotion_aixin_gif);
//        EMPTY_GIF_MAP.put("[心碎]", R.drawable.emotion_xinsui_gif);
//        EMPTY_GIF_MAP.put("[蛋糕]", R.drawable.emotion_dangao_gif);
//        EMPTY_GIF_MAP.put("[闪电]", R.drawable.emotion_shandian_gif);
//        EMPTY_GIF_MAP.put("[炸弹]", R.drawable.emotion_zhadan_gif);
//        EMPTY_GIF_MAP.put("[刀]", R.drawable.emotion_dao_gif);
//        EMPTY_GIF_MAP.put("[足球]", R.drawable.emotion_zhuqiu_gif);
//        EMPTY_GIF_MAP.put("[瓢虫]", R.drawable.emotion_pachong_gif);
//        EMPTY_GIF_MAP.put("[便便]", R.drawable.emotion_bianbian_gif);
//        EMPTY_GIF_MAP.put("[月亮]", R.drawable.emotion_yueliang_gif);
//        EMPTY_GIF_MAP.put("[太阳]", R.drawable.emotion_taiyang_gif);
//        EMPTY_GIF_MAP.put("[礼物]", R.drawable.emotion_liwu_gif);
//        EMPTY_GIF_MAP.put("[拥抱]", R.drawable.emotion_baobao_gif);
//        EMPTY_GIF_MAP.put("[强]", R.drawable.emotion_qiang_gif);
//        EMPTY_GIF_MAP.put("[弱]", R.drawable.emotion_ruo_gif);
//        EMPTY_GIF_MAP.put("[握手]", R.drawable.emotion_woshou_gif);
//        EMPTY_GIF_MAP.put("[胜利]", R.drawable.emotion_shengli_gif);
//        EMPTY_GIF_MAP.put("[抱拳]", R.drawable.emotion_baoquan_gif);
//        EMPTY_GIF_MAP.put("[勾引]", R.drawable.emotion_gouying_gif);
//        EMPTY_GIF_MAP.put("[拳头]", R.drawable.emotion_quantou_gif);
//        EMPTY_GIF_MAP.put("[差劲]", R.drawable.emotion_chajing_gif);
//        EMPTY_GIF_MAP.put("[爱你]", R.drawable.emotion_aini_gif);
//        EMPTY_GIF_MAP.put("[NO]", R.drawable.emotion_no_gif);
//        EMPTY_GIF_MAP.put("[OK]", R.drawable.emotion_ok_gif);
//        EMPTY_GIF_MAP.put("[爱情]", R.drawable.emotion_aiqing_gif);
//        EMPTY_GIF_MAP.put("[飞吻]", R.drawable.emotion_feiwen_gif);
//        EMPTY_GIF_MAP.put("[跳跳]", R.drawable.emotion_tiaotiao_gif);
//        EMPTY_GIF_MAP.put("[发抖]", R.drawable.emotion_fadou_gif);
//        EMPTY_GIF_MAP.put("[怄火]", R.drawable.emotion_ouhuo_gif);
//        EMPTY_GIF_MAP.put("[转圈]", R.drawable.emotion_zhuanquan_gif);
//        EMPTY_GIF_MAP.put("[磕头]", R.drawable.emotion_ketou_gif);
//        EMPTY_GIF_MAP.put("[回头]", R.drawable.emotion_huitou_gif);
//        EMPTY_GIF_MAP.put("[跳绳]", R.drawable.emotion_tiaosheng_gif);
//        EMPTY_GIF_MAP.put("[挥手]", R.drawable.emotion_huishou_gif);
//        EMPTY_GIF_MAP.put("[激动]", R.drawable.emotion_jidong_gif);
//        EMPTY_GIF_MAP.put("[街舞]", R.drawable.emotion_jiewu_gif);
//        EMPTY_GIF_MAP.put("[献吻]", R.drawable.emotion_xianwen_gif);
//        EMPTY_GIF_MAP.put("[左太极]", R.drawable.emotion_zuotaiji_gif);
//        EMPTY_GIF_MAP.put("[右太极]", R.drawable.emotion_youtaiji_gif);
//        EMPTY_GIF_MAP.put("[双喜]", R.drawable.emotion_shuangxi_gif);
//        EMPTY_GIF_MAP.put("[鞭炮]", R.drawable.emotion_bianpao_gif);
//        EMPTY_GIF_MAP.put("[灯笼]", R.drawable.emotion_denglong_gif);
//        EMPTY_GIF_MAP.put("[发财]", R.drawable.emotion_facai_gif);
//        EMPTY_GIF_MAP.put("[K歌]", R.drawable.emotion_kge_gif);
//        EMPTY_GIF_MAP.put("[购物]", R.drawable.emotion_gouwu_gif);
//        EMPTY_GIF_MAP.put("[邮件]", R.drawable.emotion_youjian_gif);
//        EMPTY_GIF_MAP.put("[帅]", R.drawable.emotion_dashuai_gif);
//        EMPTY_GIF_MAP.put("[喝彩]", R.drawable.emotion_hecai_gif);
//        EMPTY_GIF_MAP.put("[祈祷]", R.drawable.emotion_qidao_gif);
//        EMPTY_GIF_MAP.put("[爆筋]", R.drawable.emotion_baojing_gif);
//        EMPTY_GIF_MAP.put("[棒棒糖]", R.drawable.emotion_bangbangtang_gif);
//        EMPTY_GIF_MAP.put("[喝奶]", R.drawable.emotion_henai_gif);
//        EMPTY_GIF_MAP.put("[下面]", R.drawable.emotion_xiamian_gif);
//        EMPTY_GIF_MAP.put("[香蕉]", R.drawable.emotion_xiangjiao_gif);
//        EMPTY_GIF_MAP.put("[飞机]", R.drawable.emotion_feiji_gif);
//        EMPTY_GIF_MAP.put("[开车]", R.drawable.emotion_kaiche_gif);
//        EMPTY_GIF_MAP.put("[左车头]", R.drawable.emotion_zuochetou_gif);
//        EMPTY_GIF_MAP.put("[车厢]", R.drawable.emotion_chexiang_gif);
//        EMPTY_GIF_MAP.put("[右车头]", R.drawable.emotion_youchexiang_gif);
//        EMPTY_GIF_MAP.put("[多云]", R.drawable.emotion_duoyun_gif);
//        EMPTY_GIF_MAP.put("[下雨]", R.drawable.emotion_xiayu_gif);
//        EMPTY_GIF_MAP.put("[钞票]", R.drawable.emotion_chaopiao_gif);
//        EMPTY_GIF_MAP.put("[熊猫]", R.drawable.emotion_xiongmao_gif);
//        EMPTY_GIF_MAP.put("[灯泡]", R.drawable.emotion_dengpao_gif);
//        EMPTY_GIF_MAP.put("[风车]", R.drawable.emotion_fengche_gif);
//        EMPTY_GIF_MAP.put("[闹钟]", R.drawable.emotion_naozhong_gif);
//        EMPTY_GIF_MAP.put("[打伞]", R.drawable.emotion_dashan_gif);
//        EMPTY_GIF_MAP.put("[彩球]", R.drawable.emotion_caiqiu_gif);
//        EMPTY_GIF_MAP.put("[钻戒]", R.drawable.emotion_zhuanjie_gif);
//        EMPTY_GIF_MAP.put("[沙发]", R.drawable.emotion_shafa_gif);
//        EMPTY_GIF_MAP.put("[纸巾]", R.drawable.emotion_zhijing_gif);
//        EMPTY_GIF_MAP.put("[药]", R.drawable.emotion_yao_gif);
//        EMPTY_GIF_MAP.put("[手枪]", R.drawable.emotion_shouqiang_gif);
//        EMPTY_GIF_MAP.put("[青蛙]", R.drawable.emotion_qingwa_gif);

        EMOTION_STATIC_MAP = new LinkedHashMap<>();

        EMOTION_STATIC_MAP.put("[可爱]", R.drawable.emotion_keai);
        EMOTION_STATIC_MAP.put("[生气]", R.drawable.emotion_shengqi);
        EMOTION_STATIC_MAP.put("[痛苦]", R.drawable.emotion_tongku);
        EMOTION_STATIC_MAP.put("[惊讶]", R.drawable.emotion_jingya);
        EMOTION_STATIC_MAP.put("[得意]", R.drawable.emotion_deyi);
        EMOTION_STATIC_MAP.put("[大哭]", R.drawable.emotion_daku);
        EMOTION_STATIC_MAP.put("[困惑]", R.drawable.emotion_kunhuo);
        EMOTION_STATIC_MAP.put("[哭泣]", R.drawable.emotion_kuqi);
        EMOTION_STATIC_MAP.put("[失望]", R.drawable.emotion_shiwang);
        EMOTION_STATIC_MAP.put("[流汗]", R.drawable.emotion_liuhan);
        EMOTION_STATIC_MAP.put("[冷漠]", R.drawable.emotion_lengmo);
        EMOTION_STATIC_MAP.put("[尖叫]", R.drawable.emotion_jianjiao);
        EMOTION_STATIC_MAP.put("[刷新]", R.drawable.emotion_shuaxin);
        EMOTION_STATIC_MAP.put("[坏笑]", R.drawable.emotion_huaixiao);
        EMOTION_STATIC_MAP.put("[胜利]", R.drawable.emotion_shengli);
        EMOTION_STATIC_MAP.put("[傻笑]", R.drawable.emotion_shaxiao);
        EMOTION_STATIC_MAP.put("[开心]", R.drawable.emotion_kaixin);
        EMOTION_STATIC_MAP.put("[好色]", R.drawable.emotion_haose);
        EMOTION_STATIC_MAP.put("[调皮]", R.drawable.emotion_tiaopi);
        EMOTION_STATIC_MAP.put("[天使]", R.drawable.emotion_tianshi);
        EMOTION_STATIC_MAP.put("[笑Cry]", R.drawable.emotion_xiao);
        EMOTION_STATIC_MAP.put("[睡觉]", R.drawable.emotion_shuijiao);
        EMOTION_STATIC_MAP.put("[爱慕]", R.drawable.emotion_aimu);
        EMOTION_STATIC_MAP.put("[大笑]", R.drawable.emotion_daxiao);
        EMOTION_STATIC_MAP.put("[生病]", R.drawable.emotion_shengbing);
        EMOTION_STATIC_MAP.put("[木鸡]", R.drawable.emotion_muji);
        EMOTION_STATIC_MAP.put("[张嘴]", R.drawable.emotion_zhangzui);
        EMOTION_STATIC_MAP.put("[抱歉]", R.drawable.emotion_baoqian);
        EMOTION_STATIC_MAP.put("[难受]", R.drawable.emotion_nanshou);
        EMOTION_STATIC_MAP.put("[愤怒]", R.drawable.emotion_fennu);
        EMOTION_STATIC_MAP.put("[美滋滋]", R.drawable.emotion_meizizi);
        EMOTION_STATIC_MAP.put("[可怕]", R.drawable.emotion_kepa);
        EMOTION_STATIC_MAP.put("[飞吻]", R.drawable.emotion_feiwen);
        EMOTION_STATIC_MAP.put("[困]", R.drawable.emotion_kun);
        EMOTION_STATIC_MAP.put("[微笑]", R.drawable.emotion_weixiao);
        EMOTION_STATIC_MAP.put("[呆滞]", R.drawable.emotion_daizhi);
        EMOTION_STATIC_MAP.put("[蒙羞]", R.drawable.emotion_mengxiu);
        EMOTION_STATIC_MAP.put("[哦]", R.drawable.emotion_o);
        EMOTION_STATIC_MAP.put("[冷汗]", R.drawable.emotion_lenghan);
        EMOTION_STATIC_MAP.put("[汗颜]", R.drawable.emotion_hanyan);
        EMOTION_STATIC_MAP.put("[头晕]", R.drawable.emotion_touyun);
        EMOTION_STATIC_MAP.put("[鬼脸]", R.drawable.emotion_guilian);
        EMOTION_STATIC_MAP.put("[不屑]", R.drawable.emotion_buxie);
        EMOTION_STATIC_MAP.put("[眨眼]", R.drawable.emotion_zhayan);
        EMOTION_STATIC_MAP.put("[担心]", R.drawable.emotion_danxin);
        EMOTION_STATIC_MAP.put("[yeah]", R.drawable.emotion_yeah);
    }
}
