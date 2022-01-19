//
//  CafeteriaManager.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/28.
//

import Foundation

class CafeteriaManager{
    var cafeterias: [Cafeteria] = []
    init(){
        self.cafeterias = loadData()
    }
    
    func loadData() -> [Cafeteria]{
        var tempCafeterias: [Cafeteria] = []
        tempCafeterias.append(dormitory())
        tempCafeterias.append(gamseungcore())
        return tempCafeterias
    }
    func getCafeteria(index: Int) -> Cafeteria{
        return cafeterias[index]
    }
    
    func dormitory() -> Cafeteria{
        var menuList: [Menu] = []
        menuList.append(Menu(name: "식권1", price: "4500"))
        menuList.append(Menu(name: "식권2", price: "4000"))
        let name = "기숙사식당"
        let workTime = "07:30 ~ 18:30"
        let origin = "국내산"
        let info = Info(workTime: workTime, origin: origin)
        return Cafeteria(name: name, menu: menuList, info: info)
    }
    func gamseungcore() -> Cafeteria{
        var menuList: [Menu] = []
        menuList.append(Menu(name: "식권3", price: "3500"))
        menuList.append(Menu(name: "식권4", price: "4300"))
        let name = "감성코어"
        let workTime = "11:30 ~ 18:30"
        let origin = "김치 국내산, 소고기 호주산, 돼지고기 호주산"
        let info = Info(workTime: workTime, origin: origin)
        return Cafeteria(name: name, menu: menuList, info: info)
    }
    
    
}
