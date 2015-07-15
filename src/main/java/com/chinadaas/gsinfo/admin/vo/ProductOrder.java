package com.chinadaas.gsinfo.admin.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月10日 下午2:58:28<br>
 * @author 开发者真实姓名[裔传洲]
 */
@Entity
@Table(name = "T_PRODUCT_ORDER")
public class ProductOrder extends BaseEntity{
	
	@Id
	@Column(name = "ID", nullable = false, length = 50)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private String id;
	
	@Column(name = "CODE_PRODUCT_TPL", length = 20)
	private String code_product_tpl;
	
	@Column(name = "FEE_TYPE", length = 2)
	private String fee_type;
	
	@Column(name = "START_CNT")
	private Integer start_cnt;

	@Column(name = "END_CNT")
	private Integer end_cnt;

	@Column(name = "PRICE")
	private Integer price;

	@Column(name = "PAY_CYCLE", length = 2)
	private String pay_cycle;

	@Column(name = "EFFECT_DATE")
	private Date effect_date;

	@Column(name = "LOSE_DATE")
	private Date lose_date;
	
	@Column(name = "GIFT_VOL")
	private Integer gift_vol;

	@Column(name = "ALERT_VOL")
	private Integer alert_vol;

	@Column(name = "ID_USER_CREATE", length = 50)
	private String creator;
	
	@Column(name = "CREATE_TIME")
	private Date create_time;
	
	@Column(name = "ID_USER_MODIFY", length = 50)
	private String modifier;

	@Column(name = "MODIFY_TIME")
	private Date modify_time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode_product_tpl() {
		return code_product_tpl;
	}

	public void setCode_product_tpl(String code_product_tpl) {
		this.code_product_tpl = code_product_tpl;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public Integer getStart_cnt() {
		return start_cnt;
	}

	public void setStart_cnt(Integer start_cnt) {
		this.start_cnt = start_cnt;
	}

	public Integer getEnd_cnt() {
		return end_cnt;
	}

	public void setEnd_cnt(Integer end_cnt) {
		this.end_cnt = end_cnt;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPay_cycle() {
		return pay_cycle;
	}

	public void setPay_cycle(String pay_cycle) {
		this.pay_cycle = pay_cycle;
	}

	public Date getEffect_date() {
		return effect_date;
	}

	public void setEffect_date(Date effect_date) {
		this.effect_date = effect_date;
	}

	public Date getLose_date() {
		return lose_date;
	}

	public void setLose_date(Date lose_date) {
		this.lose_date = lose_date;
	}

	public Integer getGift_vol() {
		return gift_vol;
	}

	public void setGift_vol(Integer gift_vol) {
		this.gift_vol = gift_vol;
	}

	public Integer getAlert_vol() {
		return alert_vol;
	}

	public void setAlert_vol(Integer alert_vol) {
		this.alert_vol = alert_vol;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

}
