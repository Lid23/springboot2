package com.noodles.springbootdemos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noodles.springbootdemos.vo.resp.BaseRespVo;

/**
 * @filename AgreeProtocolController
 * @description 使用某功能时，如果是首次进入会展示一个协议页面，用户需要勾选后点确定才能进入功能，
 * 此后再进该功能，不再显示协议页直接进入该功能
 * 头条链接：https://www.toutiao.com/i6765827466045948427/?tt_from=weixin&utm_campaign=client_share&wxshare_count=1&timestamp=1586341110&app=news_article&utm_source=weixin&utm_medium=toutiao_android&req_id=202004081818290100260600752713E992&group_id=6765827466045948427
 * @author 巫威
 * @date 2020/4/8 18:44
 */

@RestController
public class AgreeProtocolController {


	/**第一版只用数据库，不用Redis*/
	@RequestMapping("/hasAgree")
	public BaseRespVo hasAgree(){
return null;
	}

}
