import React from "react";

const Input = ({ changeState, getPosts, appState }) => {
	const changeTextArea = inputValue => {
		if (inputValue.substr(inputValue.length - 1) === "\n") {
			getPosts();
		}
		changeState({
			...appState,
			input: inputValue
		});
	};
	return (
		<div className="mb-4">
			<textarea
				value={appState.input}
				className="form-control"
				placeholder="Write Your Timeline!"
				onChange={e => changeTextArea(e.target.value)}
				rows="4"
			/>
		</div>
	);
};

export default Input;
