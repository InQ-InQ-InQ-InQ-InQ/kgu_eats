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
    let menuRequestURL = "\(NetWork.baseURL)/stores/"
    
    func getCafeteriaId(index: Int) -> Int{
        return self.cafeterias[index].id
    }
    
    func searchCafeteriaIndex(restaurantId: Int) -> Int{
        for i in 0..<cafeterias.count{
            if cafeterias[i].id == restaurantId{
                return i
            }
        }
        return -1
    }
    func getMenuImage(cafeteriaId: Int){
        print("getMenuImage()")
        for i in cafeterias[cafeteriaId].menu{
            print(i.name)
        }
        
    }
    func getCafeteriaName(id: Int) -> String{
        for i in cafeterias{
            if id == i.id{
                return i.name
            }
        }
        return "getCafeteriaName Error"
    }
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
                    print("getResutaurants() JsonDecoder Error")
                }
            default:
                // TODO: 오류처리00
                print("default")
            }
        }
    }
    
    func getMenus(model: MenuModel, completion: @escaping([MenuResponseModel]) -> Void){
        let addUrlInfo = model.restaurantId
        
        AF.request(menuRequestURL+"\(addUrlInfo)/menus", method: .get, parameters: [:], encoding: URLEncoding.default, headers: model.headers).response { response in
            guard let statusCode = response.response?.statusCode else{
                print("statusCode Error")
                return
            }
            switch statusCode{
            case 200..<300:
                do{
                    let decodeData = try JSONDecoder().decode([MenuResponseModel].self, from: response.data!)
                    // decodeData는 restaurantId에 해당하는 메뉴를 받아옴
                    // restaurantId에 해당하는 cafeteria.menu 배열에 넣어줘야함
                    completion(decodeData)
                    
//                    let cafeteriaIndex = self.searchCafeteriaIndex(restaurantId: model.restaurantId)
//                    if cafeteriaIndex == -1 {
//                        print("cannot find cafeteria Index")
//                        return
//                    }
//                    for data in decodeData{
//                        CafeteriaManager.shared.cafeterias[cafeteriaIndex].menu.append(self.convertMenu(response: data))
//                    }
                
                }catch{
                    print("getMenus() JsonDecode Error")
                }
            default:
                print("default")
            }
            
            
        }
    }
    func convertMenu(response: MenuResponseModel) -> Menu{
//        let price: Int
//        let id: Int
//        let name: String
        let price = response.price
        let id = response.id
        let name = response.name
        return Menu(id: id, name: name, price: price)
    }
    
    
    func convertRestaurant(response: RestaurantResponseModel) -> Cafeteria{
        let campusId = response.campusId
        let id = response.id
        let name = response.name
        return Cafeteria(campusId: campusId, id: id, name: name)
    }
    
    
}