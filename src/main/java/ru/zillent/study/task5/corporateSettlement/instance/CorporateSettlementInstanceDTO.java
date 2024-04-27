package ru.zillent.study.task5.corporateSettlement.instance;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CorporateSettlementInstanceDTO {
    private Integer instanceId;
    private String registryTypeCode;
    private String accountType;
    private String currencyCode;
    private String branchCode;
    private String priorityCode;
    private String mdmCode;
    private String clientCode;
    private String trainRegion;
    private String counter;
    private String salesCode;

//    instanceId
//            productType
//    productCode
//            registerType
//    mdmCode
//
//            contractNumber
//    contractDate
//            priority
//    interestRatePenalty
//            minimalBalance
//    thresholdAmount
//            accountingDetails
//    rateType
//            taxPercentageRate
//    technicalOverdraftLimitAmount
//            contractId
//    BranchCode
//            IsoCurrencyCode
//    urgencyCode
//            ReferenceCode
//
//    additionalPropertiesVip
//            array
//
//+/-
//    массив дополнительных признаков для сегмента КИБ(VIP), добавлять по мере необходимости? на сегодня могут быть переданы 2 пары ключ/значение:
//            "additionalProperties":
//    {
//        "data": [
//        {
//            "key": " RailwayRegionOwn",
//                "value": "ABC",
//                "name": "Регион принадлежности железной дороги"
//        },
//        {
//            "key": "counter",
//                "value": "123",
//                "name": "Счетчик"
//        }
//           ]
//    }
//
//
//    data
//            object
//
//-
//
//
//
//    key
//            string
//    string
//-
//    Ключ
//
//
//            value
//    string
//            string
//-
//    Значение
//    instanceArrangement 
//    array
//
//+/-
//    массив Доп.Соглашений
//
//
//    ID доп.Ген.соглашения
//            GeneralAgreementId
//    string
//-
//    ID доп.Ген.соглашения
//
//    ID доп.соглашения
//            SupplementaryAgreementId
//    string
//-
//    ID доп.соглашения
//
//    Тип соглашения
//    arrangementType
//            string
//-
//    Enum, НСО/ЕЖО/СМО/ДБДС и тд, см. актуальную ЛМД
//
//    Идентификатор периодичности учета
//            shedulerJobId
//    integer
//            sequence
//-
//    Идентификатор задания/расписания
//    периодичность учета/расчета/выплаты фиксируется в поле name
//
//    Номер ДС
//    Number
//            string
//+
//    Номер ДС
//
//    Дата начала сделки
//            openingDate
//    date
//+
//    Дата заключения сделки (НСО/ЕЖО/СМО/ДБДС)
//
//    Дата окончания сделки
//            closingDate
//    date
//-
//
//
//    Дата отзыва сделки
//            CancelDate
//    date
//-
//
//
//    Срок действия сделки
//            validityDuration
//    integer
//-
//
//
//    Причина расторжения
//    cancellationReason
//            string
//-
//
//
//    Состояние/статус
//            Status
//    string
//-
//    Статус ДС: закрыт, открыт
//
//
//    Начисление начинается с (дата)
//
//    interestCalculationDate
//            date
//-
//    Начисление начинается с (дата)
//
//    Процент начисления на остаток
//    interestRate
//    float
//-
//    Процент начисления на остаток
//
//
//    Коэффициент
//            coefficient
//    float
//-
//    Показатель управления ставкой
//
//
//    Действие коэффициента
//    coefficientAction
//            string
//-
//    Повышающий/понижающий enum +/-
//
//
//    Минимум по ставке
//            minimumInterestRate
//    float
//-
//    Минимум по ставке
//
//    Коэффициент по минимальной ставке
//    minimumInterestRateCoefficient
//            string
//-
//    Коэффициент по минимальной ставке
//
//    Действие коэффициента по минимальной ставке
//            minimumInterestRateCoefficientAction
//    string
//-
//    Повышающий/понижающий enum +/-
//
//
//    Максимум по ставке
//            maximalnterestRate
//    decimal
//-
//    Максимум по ставке
//
//    Коэффициент по максимальной ставке
//    maximalnterestRateCoefficient
//            decimal
//-
//    Коэффициент по максимальной ставке
//
//    Действие коэффициента по максимальной ставке
//            maximalnterestRateCoefficientAction
//    string
//-
//    Повышающий/понижающий enum +/-
//
//

}
