//
//  InfoViewController.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/10.
//

import UIKit

class InfoViewController: UIViewController {
    
    @IBOutlet weak var name: UILabel!
    @IBOutlet weak var workTime: UILabel!
    @IBOutlet weak var origin: UILabel!
    
    var cafeteria: Cafeteria?
    
    override func viewDidLoad() {
        super.viewDidLoad()
       // updateUI()
        // Do any additional setup after loading the view.
    }
//    func updateUI(){
//        self.name.text = cafeteria?.name
//        self.workTime.text = cafeteria?.info.workTime
//        self.origin.text = cafeteria?.info.origin
//    }
}
