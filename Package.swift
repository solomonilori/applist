// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "AppList",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "AppList",
            targets: ["ApplistPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "ApplistPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/ApplistPlugin"),
        .testTarget(
            name: "ApplistPluginTests",
            dependencies: ["ApplistPlugin"],
            path: "ios/Tests/ApplistPluginTests")
    ]
)