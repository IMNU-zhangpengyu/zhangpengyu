package cn.zpy.ssm.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomtypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoomtypeExample() {
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

        public Criteria andRoomtypeidIsNull() {
            addCriterion("roomtypeid is null");
            return (Criteria) this;
        }

        public Criteria andRoomtypeidIsNotNull() {
            addCriterion("roomtypeid is not null");
            return (Criteria) this;
        }

        public Criteria andRoomtypeidEqualTo(Integer value) {
            addCriterion("roomtypeid =", value, "roomtypeid");
            return (Criteria) this;
        }

        public Criteria andRoomtypeidNotEqualTo(Integer value) {
            addCriterion("roomtypeid <>", value, "roomtypeid");
            return (Criteria) this;
        }

        public Criteria andRoomtypeidGreaterThan(Integer value) {
            addCriterion("roomtypeid >", value, "roomtypeid");
            return (Criteria) this;
        }

        public Criteria andRoomtypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("roomtypeid >=", value, "roomtypeid");
            return (Criteria) this;
        }

        public Criteria andRoomtypeidLessThan(Integer value) {
            addCriterion("roomtypeid <", value, "roomtypeid");
            return (Criteria) this;
        }

        public Criteria andRoomtypeidLessThanOrEqualTo(Integer value) {
            addCriterion("roomtypeid <=", value, "roomtypeid");
            return (Criteria) this;
        }

        public Criteria andRoomtypeidIn(List<Integer> values) {
            addCriterion("roomtypeid in", values, "roomtypeid");
            return (Criteria) this;
        }

        public Criteria andRoomtypeidNotIn(List<Integer> values) {
            addCriterion("roomtypeid not in", values, "roomtypeid");
            return (Criteria) this;
        }

        public Criteria andRoomtypeidBetween(Integer value1, Integer value2) {
            addCriterion("roomtypeid between", value1, value2, "roomtypeid");
            return (Criteria) this;
        }

        public Criteria andRoomtypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("roomtypeid not between", value1, value2, "roomtypeid");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameIsNull() {
            addCriterion("roomtypename is null");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameIsNotNull() {
            addCriterion("roomtypename is not null");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameEqualTo(String value) {
            addCriterion("roomtypename =", value, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameNotEqualTo(String value) {
            addCriterion("roomtypename <>", value, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameGreaterThan(String value) {
            addCriterion("roomtypename >", value, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameGreaterThanOrEqualTo(String value) {
            addCriterion("roomtypename >=", value, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameLessThan(String value) {
            addCriterion("roomtypename <", value, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameLessThanOrEqualTo(String value) {
            addCriterion("roomtypename <=", value, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameLike(String value) {
            addCriterion("roomtypename like", value, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameNotLike(String value) {
            addCriterion("roomtypename not like", value, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameIn(List<String> values) {
            addCriterion("roomtypename in", values, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameNotIn(List<String> values) {
            addCriterion("roomtypename not in", values, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameBetween(String value1, String value2) {
            addCriterion("roomtypename between", value1, value2, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypenameNotBetween(String value1, String value2) {
            addCriterion("roomtypename not between", value1, value2, "roomtypename");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescIsNull() {
            addCriterion("roomtypedesc is null");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescIsNotNull() {
            addCriterion("roomtypedesc is not null");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescEqualTo(String value) {
            addCriterion("roomtypedesc =", value, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescNotEqualTo(String value) {
            addCriterion("roomtypedesc <>", value, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescGreaterThan(String value) {
            addCriterion("roomtypedesc >", value, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescGreaterThanOrEqualTo(String value) {
            addCriterion("roomtypedesc >=", value, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescLessThan(String value) {
            addCriterion("roomtypedesc <", value, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescLessThanOrEqualTo(String value) {
            addCriterion("roomtypedesc <=", value, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescLike(String value) {
            addCriterion("roomtypedesc like", value, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescNotLike(String value) {
            addCriterion("roomtypedesc not like", value, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescIn(List<String> values) {
            addCriterion("roomtypedesc in", values, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescNotIn(List<String> values) {
            addCriterion("roomtypedesc not in", values, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescBetween(String value1, String value2) {
            addCriterion("roomtypedesc between", value1, value2, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypedescNotBetween(String value1, String value2) {
            addCriterion("roomtypedesc not between", value1, value2, "roomtypedesc");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgIsNull() {
            addCriterion("roomtypeimg is null");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgIsNotNull() {
            addCriterion("roomtypeimg is not null");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgEqualTo(String value) {
            addCriterion("roomtypeimg =", value, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgNotEqualTo(String value) {
            addCriterion("roomtypeimg <>", value, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgGreaterThan(String value) {
            addCriterion("roomtypeimg >", value, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgGreaterThanOrEqualTo(String value) {
            addCriterion("roomtypeimg >=", value, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgLessThan(String value) {
            addCriterion("roomtypeimg <", value, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgLessThanOrEqualTo(String value) {
            addCriterion("roomtypeimg <=", value, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgLike(String value) {
            addCriterion("roomtypeimg like", value, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgNotLike(String value) {
            addCriterion("roomtypeimg not like", value, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgIn(List<String> values) {
            addCriterion("roomtypeimg in", values, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgNotIn(List<String> values) {
            addCriterion("roomtypeimg not in", values, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgBetween(String value1, String value2) {
            addCriterion("roomtypeimg between", value1, value2, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andRoomtypeimgNotBetween(String value1, String value2) {
            addCriterion("roomtypeimg not between", value1, value2, "roomtypeimg");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeIsNull() {
            addCriterion("lastedittime is null");
            return (Criteria) this;
        }

        public Criteria andLastedittimeIsNotNull() {
            addCriterion("lastedittime is not null");
            return (Criteria) this;
        }

        public Criteria andLastedittimeEqualTo(Date value) {
            addCriterion("lastedittime =", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeNotEqualTo(Date value) {
            addCriterion("lastedittime <>", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeGreaterThan(Date value) {
            addCriterion("lastedittime >", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lastedittime >=", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeLessThan(Date value) {
            addCriterion("lastedittime <", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeLessThanOrEqualTo(Date value) {
            addCriterion("lastedittime <=", value, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeIn(List<Date> values) {
            addCriterion("lastedittime in", values, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeNotIn(List<Date> values) {
            addCriterion("lastedittime not in", values, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeBetween(Date value1, Date value2) {
            addCriterion("lastedittime between", value1, value2, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andLastedittimeNotBetween(Date value1, Date value2) {
            addCriterion("lastedittime not between", value1, value2, "lastedittime");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("parentid is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("parentid is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Integer value) {
            addCriterion("parentid =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Integer value) {
            addCriterion("parentid <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Integer value) {
            addCriterion("parentid >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("parentid >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Integer value) {
            addCriterion("parentid <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Integer value) {
            addCriterion("parentid <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Integer> values) {
            addCriterion("parentid in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Integer> values) {
            addCriterion("parentid not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Integer value1, Integer value2) {
            addCriterion("parentid between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Integer value1, Integer value2) {
            addCriterion("parentid not between", value1, value2, "parentid");
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