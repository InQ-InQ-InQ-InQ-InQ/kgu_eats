//
//  MenuClickedViewViewController.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/04.
//

import UIKit

class MenuClickedViewController: UIViewController {
    var showMenu: Menu?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(true)
        setUI()
        
    }
    func setUI(){
        self.menuName.text = showMenu?.name
        self.menuImage.image = showMenu?.image
        self.totalCost.text = showMenu?.price ?? ""+"원"
    }
    @IBOutlet weak var menuName: UILabel!
    @IBOutlet weak var menuImage: UIImageView!
    @IBOutlet weak var totalCost: UILabel!
    
    @IBAction func minusButton(_ sender: Any) {
        guard self.menuName.text != nil, var num = Int(self.menuName.text!) else{
            return
        }
        if num > 1 {
            num -= 1
            self.menuName.text = String(num)
            self.totalCost.text = String(num * Int(self.totalCost.text!)!)+"원"
        }else{
            // 수량은 0 보다 작을 수 없습니다. 팝업
        }
    }
    @IBAction func plusButton(_ sender: Any) {
        guard self.menuName.text != nil, var num = Int(self.menuName.text!) else{
            return
        }
        if num < 10{
            num += 1
            self.menuName.text = String(num)
            self.totalCost.text = String(num * Int(self.totalCost.text!)!)+"원"
        }else{
            // 10개를 초과할 수 없습니다. 팝업
        }
    }
    @IBAction func addItemToCart(_ sender: Any) {
        
    }
    
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
