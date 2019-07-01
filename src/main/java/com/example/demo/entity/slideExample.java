package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class slideExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public slideExample() {
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

        public Criteria andSlideIdIsNull() {
            addCriterion("slide_id is null");
            return (Criteria) this;
        }

        public Criteria andSlideIdIsNotNull() {
            addCriterion("slide_id is not null");
            return (Criteria) this;
        }

        public Criteria andSlideIdEqualTo(Integer value) {
            addCriterion("slide_id =", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdNotEqualTo(Integer value) {
            addCriterion("slide_id <>", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdGreaterThan(Integer value) {
            addCriterion("slide_id >", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("slide_id >=", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdLessThan(Integer value) {
            addCriterion("slide_id <", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdLessThanOrEqualTo(Integer value) {
            addCriterion("slide_id <=", value, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdIn(List<Integer> values) {
            addCriterion("slide_id in", values, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdNotIn(List<Integer> values) {
            addCriterion("slide_id not in", values, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdBetween(Integer value1, Integer value2) {
            addCriterion("slide_id between", value1, value2, "slideId");
            return (Criteria) this;
        }

        public Criteria andSlideIdNotBetween(Integer value1, Integer value2) {
            addCriterion("slide_id not between", value1, value2, "slideId");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("img_url not between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andSlideSortIsNull() {
            addCriterion("slide_sort is null");
            return (Criteria) this;
        }

        public Criteria andSlideSortIsNotNull() {
            addCriterion("slide_sort is not null");
            return (Criteria) this;
        }

        public Criteria andSlideSortEqualTo(Integer value) {
            addCriterion("slide_sort =", value, "slideSort");
            return (Criteria) this;
        }

        public Criteria andSlideSortNotEqualTo(Integer value) {
            addCriterion("slide_sort <>", value, "slideSort");
            return (Criteria) this;
        }

        public Criteria andSlideSortGreaterThan(Integer value) {
            addCriterion("slide_sort >", value, "slideSort");
            return (Criteria) this;
        }

        public Criteria andSlideSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("slide_sort >=", value, "slideSort");
            return (Criteria) this;
        }

        public Criteria andSlideSortLessThan(Integer value) {
            addCriterion("slide_sort <", value, "slideSort");
            return (Criteria) this;
        }

        public Criteria andSlideSortLessThanOrEqualTo(Integer value) {
            addCriterion("slide_sort <=", value, "slideSort");
            return (Criteria) this;
        }

        public Criteria andSlideSortIn(List<Integer> values) {
            addCriterion("slide_sort in", values, "slideSort");
            return (Criteria) this;
        }

        public Criteria andSlideSortNotIn(List<Integer> values) {
            addCriterion("slide_sort not in", values, "slideSort");
            return (Criteria) this;
        }

        public Criteria andSlideSortBetween(Integer value1, Integer value2) {
            addCriterion("slide_sort between", value1, value2, "slideSort");
            return (Criteria) this;
        }

        public Criteria andSlideSortNotBetween(Integer value1, Integer value2) {
            addCriterion("slide_sort not between", value1, value2, "slideSort");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andFirstPIsNull() {
            addCriterion("first_p is null");
            return (Criteria) this;
        }

        public Criteria andFirstPIsNotNull() {
            addCriterion("first_p is not null");
            return (Criteria) this;
        }

        public Criteria andFirstPEqualTo(String value) {
            addCriterion("first_p =", value, "firstP");
            return (Criteria) this;
        }

        public Criteria andFirstPNotEqualTo(String value) {
            addCriterion("first_p <>", value, "firstP");
            return (Criteria) this;
        }

        public Criteria andFirstPGreaterThan(String value) {
            addCriterion("first_p >", value, "firstP");
            return (Criteria) this;
        }

        public Criteria andFirstPGreaterThanOrEqualTo(String value) {
            addCriterion("first_p >=", value, "firstP");
            return (Criteria) this;
        }

        public Criteria andFirstPLessThan(String value) {
            addCriterion("first_p <", value, "firstP");
            return (Criteria) this;
        }

        public Criteria andFirstPLessThanOrEqualTo(String value) {
            addCriterion("first_p <=", value, "firstP");
            return (Criteria) this;
        }

        public Criteria andFirstPLike(String value) {
            addCriterion("first_p like", value, "firstP");
            return (Criteria) this;
        }

        public Criteria andFirstPNotLike(String value) {
            addCriterion("first_p not like", value, "firstP");
            return (Criteria) this;
        }

        public Criteria andFirstPIn(List<String> values) {
            addCriterion("first_p in", values, "firstP");
            return (Criteria) this;
        }

        public Criteria andFirstPNotIn(List<String> values) {
            addCriterion("first_p not in", values, "firstP");
            return (Criteria) this;
        }

        public Criteria andFirstPBetween(String value1, String value2) {
            addCriterion("first_p between", value1, value2, "firstP");
            return (Criteria) this;
        }

        public Criteria andFirstPNotBetween(String value1, String value2) {
            addCriterion("first_p not between", value1, value2, "firstP");
            return (Criteria) this;
        }

        public Criteria andSecondPIsNull() {
            addCriterion("second_p is null");
            return (Criteria) this;
        }

        public Criteria andSecondPIsNotNull() {
            addCriterion("second_p is not null");
            return (Criteria) this;
        }

        public Criteria andSecondPEqualTo(String value) {
            addCriterion("second_p =", value, "secondP");
            return (Criteria) this;
        }

        public Criteria andSecondPNotEqualTo(String value) {
            addCriterion("second_p <>", value, "secondP");
            return (Criteria) this;
        }

        public Criteria andSecondPGreaterThan(String value) {
            addCriterion("second_p >", value, "secondP");
            return (Criteria) this;
        }

        public Criteria andSecondPGreaterThanOrEqualTo(String value) {
            addCriterion("second_p >=", value, "secondP");
            return (Criteria) this;
        }

        public Criteria andSecondPLessThan(String value) {
            addCriterion("second_p <", value, "secondP");
            return (Criteria) this;
        }

        public Criteria andSecondPLessThanOrEqualTo(String value) {
            addCriterion("second_p <=", value, "secondP");
            return (Criteria) this;
        }

        public Criteria andSecondPLike(String value) {
            addCriterion("second_p like", value, "secondP");
            return (Criteria) this;
        }

        public Criteria andSecondPNotLike(String value) {
            addCriterion("second_p not like", value, "secondP");
            return (Criteria) this;
        }

        public Criteria andSecondPIn(List<String> values) {
            addCriterion("second_p in", values, "secondP");
            return (Criteria) this;
        }

        public Criteria andSecondPNotIn(List<String> values) {
            addCriterion("second_p not in", values, "secondP");
            return (Criteria) this;
        }

        public Criteria andSecondPBetween(String value1, String value2) {
            addCriterion("second_p between", value1, value2, "secondP");
            return (Criteria) this;
        }

        public Criteria andSecondPNotBetween(String value1, String value2) {
            addCriterion("second_p not between", value1, value2, "secondP");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
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