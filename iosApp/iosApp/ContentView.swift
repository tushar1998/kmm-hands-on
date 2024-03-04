import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
	let greet = Greeting().greet()

	var body: some View {
		Text(viewModel.roadster)
	}
}

extension ContentView {
    class ViewModel: ObservableObject {
        @Published var text = "Loading"
        @Published var roadster = "Loading Roadster"

        init () {
            Greeting().greetings {
                greeting, error in DispatchQueue.main.async {
                    if let greeting = greeting {
                        self.text = greeting
                    }else{
                        self.text = error?.localizedDescription ?? "error"
                    }
                }
            }
            
            Greeting().getAllRoadster {
                roadster, error in DispatchQueue.main.async {
                    if let roadster = roadster?.name {
                        
                       
                            self.roadster = roadster
                 
                        
                    }else{
                        self.roadster = error?.localizedDescription ?? "error"
                    }
                }
            }
        }
    }
}

