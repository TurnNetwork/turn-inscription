package com.turn.inscription.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InscriptionInventoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InscriptionInventoryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdIsNull() {
            addCriterion("inscription_id is null");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdIsNotNull() {
            addCriterion("inscription_id is not null");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdEqualTo(String value) {
            addCriterion("inscription_id =", value, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdNotEqualTo(String value) {
            addCriterion("inscription_id <>", value, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdGreaterThan(String value) {
            addCriterion("inscription_id >", value, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdGreaterThanOrEqualTo(String value) {
            addCriterion("inscription_id >=", value, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdLessThan(String value) {
            addCriterion("inscription_id <", value, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdLessThanOrEqualTo(String value) {
            addCriterion("inscription_id <=", value, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdLike(String value) {
            addCriterion("inscription_id like", value, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdNotLike(String value) {
            addCriterion("inscription_id not like", value, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdIn(List<String> values) {
            addCriterion("inscription_id in", values, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdNotIn(List<String> values) {
            addCriterion("inscription_id not in", values, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdBetween(String value1, String value2) {
            addCriterion("inscription_id between", value1, value2, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andInscriptionIdNotBetween(String value1, String value2) {
            addCriterion("inscription_id not between", value1, value2, "inscriptionId");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNull() {
            addCriterion("`owner` is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNotNull() {
            addCriterion("`owner` is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEqualTo(String value) {
            addCriterion("`owner` =", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotEqualTo(String value) {
            addCriterion("`owner` <>", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThan(String value) {
            addCriterion("`owner` >", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("`owner` >=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThan(String value) {
            addCriterion("`owner` <", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThanOrEqualTo(String value) {
            addCriterion("`owner` <=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLike(String value) {
            addCriterion("`owner` like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotLike(String value) {
            addCriterion("`owner` not like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerIn(List<String> values) {
            addCriterion("`owner` in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotIn(List<String> values) {
            addCriterion("`owner` not in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerBetween(String value1, String value2) {
            addCriterion("`owner` between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotBetween(String value1, String value2) {
            addCriterion("`owner` not between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andTickIsNull() {
            addCriterion("tick is null");
            return (Criteria) this;
        }

        public Criteria andTickIsNotNull() {
            addCriterion("tick is not null");
            return (Criteria) this;
        }

        public Criteria andTickEqualTo(String value) {
            addCriterion("tick =", value, "tick");
            return (Criteria) this;
        }

        public Criteria andTickNotEqualTo(String value) {
            addCriterion("tick <>", value, "tick");
            return (Criteria) this;
        }

        public Criteria andTickGreaterThan(String value) {
            addCriterion("tick >", value, "tick");
            return (Criteria) this;
        }

        public Criteria andTickGreaterThanOrEqualTo(String value) {
            addCriterion("tick >=", value, "tick");
            return (Criteria) this;
        }

        public Criteria andTickLessThan(String value) {
            addCriterion("tick <", value, "tick");
            return (Criteria) this;
        }

        public Criteria andTickLessThanOrEqualTo(String value) {
            addCriterion("tick <=", value, "tick");
            return (Criteria) this;
        }

        public Criteria andTickLike(String value) {
            addCriterion("tick like", value, "tick");
            return (Criteria) this;
        }

        public Criteria andTickNotLike(String value) {
            addCriterion("tick not like", value, "tick");
            return (Criteria) this;
        }

        public Criteria andTickIn(List<String> values) {
            addCriterion("tick in", values, "tick");
            return (Criteria) this;
        }

        public Criteria andTickNotIn(List<String> values) {
            addCriterion("tick not in", values, "tick");
            return (Criteria) this;
        }

        public Criteria andTickBetween(String value1, String value2) {
            addCriterion("tick between", value1, value2, "tick");
            return (Criteria) this;
        }

        public Criteria andTickNotBetween(String value1, String value2) {
            addCriterion("tick not between", value1, value2, "tick");
            return (Criteria) this;
        }

        public Criteria andExtIsNull() {
            addCriterion("ext is null");
            return (Criteria) this;
        }

        public Criteria andExtIsNotNull() {
            addCriterion("ext is not null");
            return (Criteria) this;
        }

        public Criteria andExtEqualTo(String value) {
            addCriterion("ext =", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotEqualTo(String value) {
            addCriterion("ext <>", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtGreaterThan(String value) {
            addCriterion("ext >", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtGreaterThanOrEqualTo(String value) {
            addCriterion("ext >=", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLessThan(String value) {
            addCriterion("ext <", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLessThanOrEqualTo(String value) {
            addCriterion("ext <=", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtLike(String value) {
            addCriterion("ext like", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotLike(String value) {
            addCriterion("ext not like", value, "ext");
            return (Criteria) this;
        }

        public Criteria andExtIn(List<String> values) {
            addCriterion("ext in", values, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotIn(List<String> values) {
            addCriterion("ext not in", values, "ext");
            return (Criteria) this;
        }

        public Criteria andExtBetween(String value1, String value2) {
            addCriterion("ext between", value1, value2, "ext");
            return (Criteria) this;
        }

        public Criteria andExtNotBetween(String value1, String value2) {
            addCriterion("ext not between", value1, value2, "ext");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(String value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(String value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(String value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(String value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(String value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(String value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLike(String value) {
            addCriterion("num like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotLike(String value) {
            addCriterion("num not like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<String> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<String> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(String value1, String value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(String value1, String value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(Long value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(Long value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(Long value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(Long value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(Long value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<Long> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<Long> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(Long value1, Long value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(Long value1, Long value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyIsNull() {
            addCriterion("inscription_tx_qty is null");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyIsNotNull() {
            addCriterion("inscription_tx_qty is not null");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyEqualTo(Integer value) {
            addCriterion("inscription_tx_qty =", value, "inscriptionTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyNotEqualTo(Integer value) {
            addCriterion("inscription_tx_qty <>", value, "inscriptionTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyGreaterThan(Integer value) {
            addCriterion("inscription_tx_qty >", value, "inscriptionTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("inscription_tx_qty >=", value, "inscriptionTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyLessThan(Integer value) {
            addCriterion("inscription_tx_qty <", value, "inscriptionTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyLessThanOrEqualTo(Integer value) {
            addCriterion("inscription_tx_qty <=", value, "inscriptionTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyIn(List<Integer> values) {
            addCriterion("inscription_tx_qty in", values, "inscriptionTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyNotIn(List<Integer> values) {
            addCriterion("inscription_tx_qty not in", values, "inscriptionTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyBetween(Integer value1, Integer value2) {
            addCriterion("inscription_tx_qty between", value1, value2, "inscriptionTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionTxQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("inscription_tx_qty not between", value1, value2, "inscriptionTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyIsNull() {
            addCriterion("inscription_owner_tx_qty is null");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyIsNotNull() {
            addCriterion("inscription_owner_tx_qty is not null");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyEqualTo(Integer value) {
            addCriterion("inscription_owner_tx_qty =", value, "inscriptionOwnerTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyNotEqualTo(Integer value) {
            addCriterion("inscription_owner_tx_qty <>", value, "inscriptionOwnerTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyGreaterThan(Integer value) {
            addCriterion("inscription_owner_tx_qty >", value, "inscriptionOwnerTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("inscription_owner_tx_qty >=", value, "inscriptionOwnerTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyLessThan(Integer value) {
            addCriterion("inscription_owner_tx_qty <", value, "inscriptionOwnerTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyLessThanOrEqualTo(Integer value) {
            addCriterion("inscription_owner_tx_qty <=", value, "inscriptionOwnerTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyIn(List<Integer> values) {
            addCriterion("inscription_owner_tx_qty in", values, "inscriptionOwnerTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyNotIn(List<Integer> values) {
            addCriterion("inscription_owner_tx_qty not in", values, "inscriptionOwnerTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyBetween(Integer value1, Integer value2) {
            addCriterion("inscription_owner_tx_qty between", value1, value2, "inscriptionOwnerTxQty");
            return (Criteria) this;
        }

        public Criteria andInscriptionOwnerTxQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("inscription_owner_tx_qty not between", value1, value2, "inscriptionOwnerTxQty");
            return (Criteria) this;
        }

        public Criteria andRetryNumIsNull() {
            addCriterion("retry_num is null");
            return (Criteria) this;
        }

        public Criteria andRetryNumIsNotNull() {
            addCriterion("retry_num is not null");
            return (Criteria) this;
        }

        public Criteria andRetryNumEqualTo(Integer value) {
            addCriterion("retry_num =", value, "retryNum");
            return (Criteria) this;
        }

        public Criteria andRetryNumNotEqualTo(Integer value) {
            addCriterion("retry_num <>", value, "retryNum");
            return (Criteria) this;
        }

        public Criteria andRetryNumGreaterThan(Integer value) {
            addCriterion("retry_num >", value, "retryNum");
            return (Criteria) this;
        }

        public Criteria andRetryNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("retry_num >=", value, "retryNum");
            return (Criteria) this;
        }

        public Criteria andRetryNumLessThan(Integer value) {
            addCriterion("retry_num <", value, "retryNum");
            return (Criteria) this;
        }

        public Criteria andRetryNumLessThanOrEqualTo(Integer value) {
            addCriterion("retry_num <=", value, "retryNum");
            return (Criteria) this;
        }

        public Criteria andRetryNumIn(List<Integer> values) {
            addCriterion("retry_num in", values, "retryNum");
            return (Criteria) this;
        }

        public Criteria andRetryNumNotIn(List<Integer> values) {
            addCriterion("retry_num not in", values, "retryNum");
            return (Criteria) this;
        }

        public Criteria andRetryNumBetween(Integer value1, Integer value2) {
            addCriterion("retry_num between", value1, value2, "retryNum");
            return (Criteria) this;
        }

        public Criteria andRetryNumNotBetween(Integer value1, Integer value2) {
            addCriterion("retry_num not between", value1, value2, "retryNum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}