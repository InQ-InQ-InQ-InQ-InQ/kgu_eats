//
//  CafeteriaManager.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/28.
//

import Foundation
import Alamofire

class CafeteriaManager{
    static let shared = CafeteriaManager()
    
    var cafeterias = [Cafeteria]()
    
    let restarantRequestURL = "\(NetWork.baseURL)/stores"
    
//    init(){
//        self.cafeterias = loadData()
//    }
//
//    func loadData() -> [Cafeteria]{
//        var tempCafeterias: [Cafeteria] = []
//        tempCafeterias.append(dormitory())
//        tempCafeterias.append(gamseungcore())
//        return tempCafeterias
//    }
//    func getCafeteria(index: Int) -> Cafeteria{
//        return cafeterias[index]
//    }
//
//    func dormitory() -> Cafeteria{
//
//        let name = "기숙사식당"
//        let workTime = "07:30 ~ 18:30"
//        let origin = "국내산"
//        let info = Info(workTime: workTime, origin: origin)
//        let nextCafeteriaId = CafeteriaManager.cafeteriaId
//        CafeteriaManager.cafeteriaId = nextCafeteriaId + 1
//
//        var menuList: [Menu] = []
//        let nextMenuId = CafeteriaManager.menuId
//        CafeteriaManager.menuId = CafeteriaManager.menuId + 1
//        menuList.append(Menu(menuId: nextMenuId, storeId: nextCafeteriaId, name: "식권1", price: "4500"))
//        return Cafeteria(name: name, menu: menuList, info: info, id: nextCafeteriaId)
//    }
//    func gamseungcore() -> Cafeteria{
//
//        let name = "감성코어"
//        let workTime = "11:30 ~ 18:30"
//        let origin = "김치 국내산, 소고기 호주산, 돼지고기 호주산"
//        let info = Info(workTime: workTime, origin: origin)
//        let nextCafeteriaId = CafeteriaManager.cafeteriaId
//        CafeteriaManager.cafeteriaId = nextCafeteriaId + 1
//
//
//        let nextMenuId = CafeteriaManager.menuId
//        CafeteriaManager.menuId = CafeteriaManager.menuId + 1
//
//
//        var menuList: [Menu] = []
//        menuList.append(Menu(menuId: nextMenuId, storeId: nextCafeteriaId, name: "돈까스", price: "3500"))
//
//        return Cafeteria(name: name, menu: menuList, info: info, id: nextCafeteriaId)
//    }
}
extension CafeteriaManager{
    func getRestaurants(model: RestaurantInfoModel, completion: @escaping([RestaurantResponseModel]) -> Void ){
        AF.request(restarantRequestURL, method: .get, parameters: [:], encoding: URLEncoding.default, headers: model.headers).response { response in
            guard let statusCode = response.response?.statusCode else{
                print("statusCode error")
                return
            }
    
            switch statusCode{
            case 200..<300:
                do{
                    let decodeData = try JSONDecoder().decode([RestaurantResponseModel].self, from: response.data!)
                    completion(decodeData)
//                    for i in decodeData{
//                        let cafeteriaModel = self.convertResponseToModel(response: i)
//                        CafeteriaManager.shared.cafeterias.append(cafeteriaModel)
//                    }
//                    print(CafeteriaManager.shared.cafeterias.count)
                    
                }catch{
                    print("CafeteriaManager JsonDecoder Error")
                }
            default:
                // TODO: 오류처리00
                print("default")
            }
        }
    }
    
    func convertResponseToModel(response: RestaurantResponseModel) -> Cafeteria{
        let campusId = response.campusId
        let id = response.id
        let name = response.name
        return Cafeteria(campusId: campusId, id: id, name: name)
    }
    
    
}
