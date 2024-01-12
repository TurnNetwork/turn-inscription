package com.turn.inscription.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InscriptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InscriptionExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andContractAddressIsNull() {
            addCriterion("contract_address is null");
            return (Criteria) this;
        }

        public Criteria andContractAddressIsNotNull() {
            addCriterion("contract_address is not null");
            return (Criteria) this;
        }

        public Criteria andContractAddressEqualTo(String value) {
            addCriterion("contract_address =", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotEqualTo(String value) {
            addCriterion("contract_address <>", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressGreaterThan(String value) {
            addCriterion("contract_address >", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressGreaterThanOrEqualTo(String value) {
            addCriterion("contract_address >=", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressLessThan(String value) {
            addCriterion("contract_address <", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressLessThanOrEqualTo(String value) {
            addCriterion("contract_address <=", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressLike(String value) {
            addCriterion("contract_address like", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotLike(String value) {
            addCriterion("contract_address not like", value, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressIn(List<String> values) {
            addCriterion("contract_address in", values, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotIn(List<String> values) {
            addCriterion("contract_address not in", values, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressBetween(String value1, String value2) {
            addCriterion("contract_address between", value1, value2, "contractAddress");
            return (Criteria) this;
        }

        public Criteria andContractAddressNotBetween(String value1, String value2) {
            addCriterion("contract_address not between", value1, value2, "contractAddress");
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

        public Criteria andTotalSupplyIsNull() {
            addCriterion("total_supply is null");
            return (Criteria) this;
        }

        public Criteria andTotalSupplyIsNotNull() {
            addCriterion("total_supply is not null");
            return (Criteria) this;
        }

        public Criteria andTotalSupplyEqualTo(Long value) {
            addCriterion("total_supply =", value, "totalSupply");
            return (Criteria) this;
        }

        public Criteria andTotalSupplyNotEqualTo(Long value) {
            addCriterion("total_supply <>", value, "totalSupply");
            return (Criteria) this;
        }

        public Criteria andTotalSupplyGreaterThan(Long value) {
            addCriterion("total_supply >", value, "totalSupply");
            return (Criteria) this;
        }

        public Criteria andTotalSupplyGreaterThanOrEqualTo(Long value) {
            addCriterion("total_supply >=", value, "totalSupply");
            return (Criteria) this;
        }

        public Criteria andTotalSupplyLessThan(Long value) {
            addCriterion("total_supply <", value, "totalSupply");
            return (Criteria) this;
        }

        public Criteria andTotalSupplyLessThanOrEqualTo(Long value) {
            addCriterion("total_supply <=", value, "totalSupply");
            return (Criteria) this;
        }

        public Criteria andTotalSupplyIn(List<Long> values) {
            addCriterion("total_supply in", values, "totalSupply");
            return (Criteria) this;
        }

        public Criteria andTotalSupplyNotIn(List<Long> values) {
            addCriterion("total_supply not in", values, "totalSupply");
            return (Criteria) this;
        }

        public Criteria andTotalSupplyBetween(Long value1, Long value2) {
            addCriterion("total_supply between", value1, value2, "totalSupply");
            return (Criteria) this;
        }

        public Criteria andTotalSupplyNotBetween(Long value1, Long value2) {
            addCriterion("total_supply not between", value1, value2, "totalSupply");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintIsNull() {
            addCriterion("limit_per_mint is null");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintIsNotNull() {
            addCriterion("limit_per_mint is not null");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintEqualTo(Long value) {
            addCriterion("limit_per_mint =", value, "limitPerMint");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintNotEqualTo(Long value) {
            addCriterion("limit_per_mint <>", value, "limitPerMint");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintGreaterThan(Long value) {
            addCriterion("limit_per_mint >", value, "limitPerMint");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintGreaterThanOrEqualTo(Long value) {
            addCriterion("limit_per_mint >=", value, "limitPerMint");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintLessThan(Long value) {
            addCriterion("limit_per_mint <", value, "limitPerMint");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintLessThanOrEqualTo(Long value) {
            addCriterion("limit_per_mint <=", value, "limitPerMint");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintIn(List<Long> values) {
            addCriterion("limit_per_mint in", values, "limitPerMint");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintNotIn(List<Long> values) {
            addCriterion("limit_per_mint not in", values, "limitPerMint");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintBetween(Long value1, Long value2) {
            addCriterion("limit_per_mint between", value1, value2, "limitPerMint");
            return (Criteria) this;
        }

        public Criteria andLimitPerMintNotBetween(Long value1, Long value2) {
            addCriterion("limit_per_mint not between", value1, value2, "limitPerMint");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletIsNull() {
            addCriterion("max_per_wallet is null");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletIsNotNull() {
            addCriterion("max_per_wallet is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletEqualTo(Long value) {
            addCriterion("max_per_wallet =", value, "maxPerWallet");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletNotEqualTo(Long value) {
            addCriterion("max_per_wallet <>", value, "maxPerWallet");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletGreaterThan(Long value) {
            addCriterion("max_per_wallet >", value, "maxPerWallet");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletGreaterThanOrEqualTo(Long value) {
            addCriterion("max_per_wallet >=", value, "maxPerWallet");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletLessThan(Long value) {
            addCriterion("max_per_wallet <", value, "maxPerWallet");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletLessThanOrEqualTo(Long value) {
            addCriterion("max_per_wallet <=", value, "maxPerWallet");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletIn(List<Long> values) {
            addCriterion("max_per_wallet in", values, "maxPerWallet");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletNotIn(List<Long> values) {
            addCriterion("max_per_wallet not in", values, "maxPerWallet");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletBetween(Long value1, Long value2) {
            addCriterion("max_per_wallet between", value1, value2, "maxPerWallet");
            return (Criteria) this;
        }

        public Criteria andMaxPerWalletNotBetween(Long value1, Long value2) {
            addCriterion("max_per_wallet not between", value1, value2, "maxPerWallet");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueIsNull() {
            addCriterion("need_extend_value is null");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueIsNotNull() {
            addCriterion("need_extend_value is not null");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueEqualTo(Integer value) {
            addCriterion("need_extend_value =", value, "needExtendValue");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueNotEqualTo(Integer value) {
            addCriterion("need_extend_value <>", value, "needExtendValue");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueGreaterThan(Integer value) {
            addCriterion("need_extend_value >", value, "needExtendValue");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_extend_value >=", value, "needExtendValue");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueLessThan(Integer value) {
            addCriterion("need_extend_value <", value, "needExtendValue");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueLessThanOrEqualTo(Integer value) {
            addCriterion("need_extend_value <=", value, "needExtendValue");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueIn(List<Integer> values) {
            addCriterion("need_extend_value in", values, "needExtendValue");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueNotIn(List<Integer> values) {
            addCriterion("need_extend_value not in", values, "needExtendValue");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueBetween(Integer value1, Integer value2) {
            addCriterion("need_extend_value between", value1, value2, "needExtendValue");
            return (Criteria) this;
        }

        public Criteria andNeedExtendValueNotBetween(Integer value1, Integer value2) {
            addCriterion("need_extend_value not between", value1, value2, "needExtendValue");
            return (Criteria) this;
        }

        public Criteria andDeployByIsNull() {
            addCriterion("deploy_by is null");
            return (Criteria) this;
        }

        public Criteria andDeployByIsNotNull() {
            addCriterion("deploy_by is not null");
            return (Criteria) this;
        }

        public Criteria andDeployByEqualTo(String value) {
            addCriterion("deploy_by =", value, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployByNotEqualTo(String value) {
            addCriterion("deploy_by <>", value, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployByGreaterThan(String value) {
            addCriterion("deploy_by >", value, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployByGreaterThanOrEqualTo(String value) {
            addCriterion("deploy_by >=", value, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployByLessThan(String value) {
            addCriterion("deploy_by <", value, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployByLessThanOrEqualTo(String value) {
            addCriterion("deploy_by <=", value, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployByLike(String value) {
            addCriterion("deploy_by like", value, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployByNotLike(String value) {
            addCriterion("deploy_by not like", value, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployByIn(List<String> values) {
            addCriterion("deploy_by in", values, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployByNotIn(List<String> values) {
            addCriterion("deploy_by not in", values, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployByBetween(String value1, String value2) {
            addCriterion("deploy_by between", value1, value2, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployByNotBetween(String value1, String value2) {
            addCriterion("deploy_by not between", value1, value2, "deployBy");
            return (Criteria) this;
        }

        public Criteria andDeployTimeIsNull() {
            addCriterion("deploy_time is null");
            return (Criteria) this;
        }

        public Criteria andDeployTimeIsNotNull() {
            addCriterion("deploy_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeployTimeEqualTo(Date value) {
            addCriterion("deploy_time =", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeNotEqualTo(Date value) {
            addCriterion("deploy_time <>", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeGreaterThan(Date value) {
            addCriterion("deploy_time >", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deploy_time >=", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeLessThan(Date value) {
            addCriterion("deploy_time <", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeLessThanOrEqualTo(Date value) {
            addCriterion("deploy_time <=", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeIn(List<Date> values) {
            addCriterion("deploy_time in", values, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeNotIn(List<Date> values) {
            addCriterion("deploy_time not in", values, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeBetween(Date value1, Date value2) {
            addCriterion("deploy_time between", value1, value2, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeNotBetween(Date value1, Date value2) {
            addCriterion("deploy_time not between", value1, value2, "deployTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andMintedIsNull() {
            addCriterion("minted is null");
            return (Criteria) this;
        }

        public Criteria andMintedIsNotNull() {
            addCriterion("minted is not null");
            return (Criteria) this;
        }

        public Criteria andMintedEqualTo(Long value) {
            addCriterion("minted =", value, "minted");
            return (Criteria) this;
        }

        public Criteria andMintedNotEqualTo(Long value) {
            addCriterion("minted <>", value, "minted");
            return (Criteria) this;
        }

        public Criteria andMintedGreaterThan(Long value) {
            addCriterion("minted >", value, "minted");
            return (Criteria) this;
        }

        public Criteria andMintedGreaterThanOrEqualTo(Long value) {
            addCriterion("minted >=", value, "minted");
            return (Criteria) this;
        }

        public Criteria andMintedLessThan(Long value) {
            addCriterion("minted <", value, "minted");
            return (Criteria) this;
        }

        public Criteria andMintedLessThanOrEqualTo(Long value) {
            addCriterion("minted <=", value, "minted");
            return (Criteria) this;
        }

        public Criteria andMintedIn(List<Long> values) {
            addCriterion("minted in", values, "minted");
            return (Criteria) this;
        }

        public Criteria andMintedNotIn(List<Long> values) {
            addCriterion("minted not in", values, "minted");
            return (Criteria) this;
        }

        public Criteria andMintedBetween(Long value1, Long value2) {
            addCriterion("minted between", value1, value2, "minted");
            return (Criteria) this;
        }

        public Criteria andMintedNotBetween(Long value1, Long value2) {
            addCriterion("minted not between", value1, value2, "minted");
            return (Criteria) this;
        }

        public Criteria andHoldersIsNull() {
            addCriterion("holders is null");
            return (Criteria) this;
        }

        public Criteria andHoldersIsNotNull() {
            addCriterion("holders is not null");
            return (Criteria) this;
        }

        public Criteria andHoldersEqualTo(Integer value) {
            addCriterion("holders =", value, "holders");
            return (Criteria) this;
        }

        public Criteria andHoldersNotEqualTo(Integer value) {
            addCriterion("holders <>", value, "holders");
            return (Criteria) this;
        }

        public Criteria andHoldersGreaterThan(Integer value) {
            addCriterion("holders >", value, "holders");
            return (Criteria) this;
        }

        public Criteria andHoldersGreaterThanOrEqualTo(Integer value) {
            addCriterion("holders >=", value, "holders");
            return (Criteria) this;
        }

        public Criteria andHoldersLessThan(Integer value) {
            addCriterion("holders <", value, "holders");
            return (Criteria) this;
        }

        public Criteria andHoldersLessThanOrEqualTo(Integer value) {
            addCriterion("holders <=", value, "holders");
            return (Criteria) this;
        }

        public Criteria andHoldersIn(List<Integer> values) {
            addCriterion("holders in", values, "holders");
            return (Criteria) this;
        }

        public Criteria andHoldersNotIn(List<Integer> values) {
            addCriterion("holders not in", values, "holders");
            return (Criteria) this;
        }

        public Criteria andHoldersBetween(Integer value1, Integer value2) {
            addCriterion("holders between", value1, value2, "holders");
            return (Criteria) this;
        }

        public Criteria andHoldersNotBetween(Integer value1, Integer value2) {
            addCriterion("holders not between", value1, value2, "holders");
            return (Criteria) this;
        }

        public Criteria andTotalTxIsNull() {
            addCriterion("total_tx is null");
            return (Criteria) this;
        }

        public Criteria andTotalTxIsNotNull() {
            addCriterion("total_tx is not null");
            return (Criteria) this;
        }

        public Criteria andTotalTxEqualTo(Long value) {
            addCriterion("total_tx =", value, "totalTx");
            return (Criteria) this;
        }

        public Criteria andTotalTxNotEqualTo(Long value) {
            addCriterion("total_tx <>", value, "totalTx");
            return (Criteria) this;
        }

        public Criteria andTotalTxGreaterThan(Long value) {
            addCriterion("total_tx >", value, "totalTx");
            return (Criteria) this;
        }

        public Criteria andTotalTxGreaterThanOrEqualTo(Long value) {
            addCriterion("total_tx >=", value, "totalTx");
            return (Criteria) this;
        }

        public Criteria andTotalTxLessThan(Long value) {
            addCriterion("total_tx <", value, "totalTx");
            return (Criteria) this;
        }

        public Criteria andTotalTxLessThanOrEqualTo(Long value) {
            addCriterion("total_tx <=", value, "totalTx");
            return (Criteria) this;
        }

        public Criteria andTotalTxIn(List<Long> values) {
            addCriterion("total_tx in", values, "totalTx");
            return (Criteria) this;
        }

        public Criteria andTotalTxNotIn(List<Long> values) {
            addCriterion("total_tx not in", values, "totalTx");
            return (Criteria) this;
        }

        public Criteria andTotalTxBetween(Long value1, Long value2) {
            addCriterion("total_tx between", value1, value2, "totalTx");
            return (Criteria) this;
        }

        public Criteria andTotalTxNotBetween(Long value1, Long value2) {
            addCriterion("total_tx not between", value1, value2, "totalTx");
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